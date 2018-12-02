//
//  SearchViewModel.swift
//  SentimentAnalysis
//
//  Created by Thales Frigo on 01/12/18.
//  Copyright © 2018 Thales Frigo. All rights reserved.
//

import Foundation

protocol SearchViewModelDelegate: AnyObject {
    func didStartProcessTweet(with tweet: Tweet)
}

class SearchViewModel {
    
    let repository: SearchRepositoryType
    
    var dataSource: Variable<[Tweet]> = Variable([])
    var searchState: Variable<ViewModelState<[Tweet]>> = Variable(.empty)
    
    weak var delegate: SearchViewModelDelegate?
    
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
                    self?.searchState.value = .empty
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
