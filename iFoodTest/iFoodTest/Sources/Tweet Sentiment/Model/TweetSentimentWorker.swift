//
//  TweetSentimentWorker.swift
//  iFoodTest
//
//  Created by Mauricio Cesar Maniglia Junior on 09/06/18.
//  Copyright © 2018 Mauricio Cesar Maniglia Junior. All rights reserved.
//

import Foundation

final class TweetSentimentWorker: TweetSentimentStoreProtocol {
    fileprivate var store: TweetSentimentStoreProtocol
    
    init(store: TweetSentimentStoreProtocol = TweetSentimentAPIStore()) {
        self.store = store
    }
    
    func fetchSentimentAnalysisForTweet(_ tweet: Tweet) {
        store.fetchSentimentAnalysisForTweet(tweet)
    }
}
