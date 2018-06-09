//
//  TweetListInteractor.swift
//  iFoodTest
//
//  Created by Mauricio Cesar Maniglia Junior on 07/06/18.
//  Copyright © 2018 Mauricio Cesar Maniglia Junior. All rights reserved.
//

import Foundation

final class TweetListInteractor: TweetListInteractorProtocol {
    
    var output: TweetListInteractorOutputProtocol!
    let worker: TweetListWorker
    
    init(output: TweetListInteractorOutputProtocol, worker: TweetListWorker = TweetListWorker()) {
        
        self.output = output
        self.worker = worker
    }
    
    func fetchTweetsForUser(_ user: String) {
        
        worker.fetchTweets(forUser: user) { [weak self] tweets, error in
            //self?.output.tweetsFetched(tweets)
            
            if let strongSelf = self {
                if let tweetError = error {
                    //strongSelf.output.presentError(error: tweetError)
                } else if let tweetsArray = tweets {
                    strongSelf.output.tweetsFetched(tweetsArray)
                }
            }
        }
    }
}
