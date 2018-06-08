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
    
    func fetchTweets() {
        
        worker.fetchTweets { (tweets, error) in
            
        }
    }
}
