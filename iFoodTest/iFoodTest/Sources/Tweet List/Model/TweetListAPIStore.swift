//
//  TweetListAPIStore.swift
//  iFoodTest
//
//  Created by Mauricio Cesar Maniglia Junior on 07/06/18.
//  Copyright © 2018 Mauricio Cesar Maniglia Junior. All rights reserved.
//

import Foundation

enum TweetListStoreError: Error {
    case generic
    case invalidURL
    case invalidResponse
    case invalidTwitterUser
}

final class TweetListAPIStore: TweetListStoreProtocol {
    
    fileprivate struct Constants {
        static let accessToken = "access_token"
    }
    
    fileprivate let networkClient: NetworkClientProtocol
    
    init(networkClient: NetworkClientProtocol = NetworkClient.sharedInstance) {
        self.networkClient = networkClient
    }
    
    func fetchTweets(forUser user: String, completion: @escaping ([Tweet]?, TweetListStoreError?) -> ()) {
        checkAuthentication { (success, error) in
            if success {
                let request = TwitterAPIEndPoint.getLastestTweets(user).urlRequest
                
                self.networkClient.sendRequest(request: request) { (data, response, error) in
                    var tweets: [Tweet]?
                    
                    if let _ = error {
                        completion([], TweetListStoreError.generic)
                        return
                    }
                    
                    if let jsonArray = data?.jsonArray() {
                        tweets = jsonArray.flatMap { tweetDictionary -> Tweet? in
                            return Tweet.fromJSON(json: tweetDictionary)
                        }
                        completion(tweets, nil)
                    } else {
                        completion([], TweetListStoreError.invalidTwitterUser)
                    }
                }
            } else {
                completion([], TweetListStoreError.invalidResponse)
            }
        }
    }
    
    fileprivate func checkAuthentication(completion: @escaping (Bool, Error?) -> ()) {
        let accessToken = TwitterAccessToken.accessToken()
        if accessToken.isEmpty == false {
            completion(true, nil)
            return
        }
        
        authenticate { (success, error) in
            if success {
                completion(success, nil)
            } else {
                completion(false, error)
            }
        }
    }
    
    fileprivate func authenticate(completion: @escaping (Bool, Error?) -> ()) {
        let request = TwitterAPIEndPoint.getAccessToken.urlRequest
        
        networkClient.sendRequest(request: request) { (data, response, error) in
            var tweetError: Error?
            var success = false
            
            if let json = data?.jsonDictionary() {
                if let accessToken = json[Constants.accessToken] {
                    TwitterAccessToken.saveToken(accessToken as! String)
                    success = true
                } else {
                    tweetError = TweetListStoreError.invalidResponse
                }
            } else {
                tweetError = TweetListStoreError.invalidResponse
            }
            
            completion(success, tweetError)
        }
    }
}
