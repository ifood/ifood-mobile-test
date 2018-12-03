//
//  SearchViewModel.swift
//  SentimentAnalysis
//
//  Created by Thales Frigo on 01/12/18.
//  Copyright © 2018 Thales Frigo. All rights reserved.
//

import Foundation
import Result

protocol SearchViewModelDelegate: AnyObject {
    func didStartProcessTweet(with tweet: Tweet)
}

class SearchViewModel {
    
    weak var delegate: SearchViewModelDelegate?
    
    let repository: SearchRepositoryType
    
    var searchState: Variable<ViewModelState<[Tweet], AnyError>> = Variable(.empty)
    
    var dataSource: Variable<[Tweet]> = Variable([])
    
    var loadingState: Variable<Bool> = Variable(false)
    var maxId: Variable<Int64?> = Variable(nil)
    var searchInput: Variable<String> = Variable("")
    var hasMoreData: Variable<Bool> = Variable(false)
    
    init(repository: SearchRepositoryType) {
        self.repository = repository
    }
    
    func search(with username: String) {
        searchInput.value = username
        searchState.value = .loading
        repository.search(with: username, maxId: maxId.value) { [weak self] (result) in
            switch result {
            case .success(let tweets):
                guard tweets.count > 0 else {
                    self?.dataSource.value = []
                    self?.searchState.value = .error(AnyError(SearchRepositoryError.userHasNoData))
                    self?.maxId.value = nil
                    self?.hasMoreData.value = false
                    return
                }
                self?.dataSource.value = tweets
                self?.searchState.value = .load(tweets)
                self?.maxId.value = tweets.last?.id
                self?.hasMoreData.value = true
            case .failure(let error):
                self?.dataSource.value = []
                self?.searchState.value = .error(error)
                self?.maxId.value = nil
                self?.hasMoreData.value = false
            }
        }
    }

    func loadMore() {
        guard !loadingState.value, hasMoreData.value, maxId.value != nil else {
            return
        }
        
        loadingState.value = true
        repository.search(with: searchInput.value, maxId: maxId.value) { [weak self] (result) in
            self?.loadingState.value = false
            switch result {
            case .success(let tweets):
                guard
                    tweets.count > 0,
                    let filteredDataSource = self?.dataSource.value.filter({ (tweet) -> Bool in
                        return tweet.id == tweets.last!.id
                    }),
                    filteredDataSource.count == 0
                    else {
                    self?.hasMoreData.value = false
                    return
                }
                
                self?.dataSource.value.append(contentsOf: tweets)
                self?.maxId.value = tweets.last?.id
                self?.hasMoreData.value = true
            case .failure:
                self?.maxId.value = nil
                self?.hasMoreData.value = false
            }
        }
    }
    
    func reset() {
        searchInput.value = ""
        searchState.value = .empty
        dataSource.value = []
        loadingState.value = false
        maxId.value = nil
        hasMoreData.value = false
    }
    
    func shouldChangeText(with text: String) -> Bool {
        var allowedCharacters = CharacterSet.alphanumerics
        allowedCharacters.insert(charactersIn: "_")
        allowedCharacters.insert(charactersIn: "\n")
        
        let characterSet = CharacterSet(charactersIn: text)
        return allowedCharacters.isSuperset(of: characterSet)
    }
    
    var numberOfItems: Int {
        return dataSource.value.count
    }
    
    func item(at index: Int) -> Tweet {
        return dataSource.value[index]
    }
    
    func process(with index: Int) {
        let tweet = item(at: index)
        delegate?.didStartProcessTweet(with: tweet)
    }
}
