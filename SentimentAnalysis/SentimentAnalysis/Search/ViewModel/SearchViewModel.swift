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
    
    
    
    
    init(repository: SearchRepositoryType) {
        self.repository = repository
    }
    
    func search(with username: String) {
        searchState.value = .loading
        repository.search(with: username) { [weak self] (result) in
            switch result {
            case .success(let tweets):
                guard tweets.count > 0 else {
                    self?.dataSource.value = []
                    self?.searchState.value = .error(AnyError(SearchRepositoryError.userHasNoData))
                    return
                }
                self?.dataSource.value = tweets
                self?.searchState.value = .load(tweets)
            case .failure(let error):
                self?.dataSource.value = []
                self?.searchState.value = .error(error)
            }
        }
    }
    
    func reset() {
        searchState.value = .empty
        dataSource.value = []
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
