//
//  SentimentViewModel.swift
//  SentimentAnalysis
//
//  Created by Thales Frigo on 02/12/18.
//  Copyright © 2018 Thales Frigo. All rights reserved.
//

import Foundation

class SentimentViewModel {
    
    let tweet: Tweet
    let repository: SentimentRepositoryType
    
    let state = Variable<ViewModelState<Sentiment, Error>>(.empty)
    
    init(tweet: Tweet, repository: SentimentRepositoryType) {
        self.tweet = tweet
        self.repository = repository
    }
    
    func analyzeDocument() {
        state.value = .loading
        repository.analyzeDocument(with: tweet.text) { [weak self] (result) in
            switch result {
            case .success(let sentiment):
                self?.state.value = .load(sentiment)
            case .failure(let error):
                self?.state.value = .error(error)
            }
        }
    }
}
