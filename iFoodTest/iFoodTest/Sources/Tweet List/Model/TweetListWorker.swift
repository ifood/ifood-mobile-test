//
//  TweetListWorker.swift
//  iFoodTest
//
//  Created by Mauricio Cesar Maniglia Junior on 07/06/18.
//  Copyright © 2018 Mauricio Cesar Maniglia Junior. All rights reserved.
//

import Foundation

final class TweetListWorker {
    
    fileprivate var store: TweetListStoreProtocol
    
    init(store: TweetListStoreProtocol = TweetListAPIStore()) {
        self.store = store
    }
    
    func fetchTweets(forUser user: String, completion: @escaping ([Tweet]?, Error?) -> ()) {        
        store.fetchTweets(forUser: user, completion: completion)
    }
}
