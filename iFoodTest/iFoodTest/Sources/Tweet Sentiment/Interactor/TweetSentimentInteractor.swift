//
//  TweetSentimentInteractor.swift
//  iFoodTest
//
//  Created by Mauricio Cesar Maniglia Junior on 09/06/18.
//  Copyright © 2018 Mauricio Cesar Maniglia Junior. All rights reserved.
//

import Foundation

final class TweetSentimentInteractor: TweetSentimentInteractorProtocol {
    var output: TweetSentimentInteractorOutputProtocol!
    let worker: TweetSentimentWorker
    
    init(output: TweetSentimentInteractorOutputProtocol, worker: TweetSentimentWorker = TweetSentimentWorker()) {
        
        self.output = output
        self.worker = worker
    }
    
    func fetchSentimentAnalysisForTweet(_ tweet: Tweet) {
        worker.fetchSentimentAnalysisForTweet(tweet)
    }
}
