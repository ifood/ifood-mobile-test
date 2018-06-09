//
//  TweetListAPIStore.swift
//  iFoodTest
//
//  Created by Mauricio Cesar Maniglia Junior on 07/06/18.
//  Copyright © 2018 Mauricio Cesar Maniglia Junior. All rights reserved.
//

import Foundation

final class TweetListAPIStore: TweetListStoreProtocol {
    
    enum TweetListStoreError: Error {
        case generic
        case invalidURL
        case invalidResponse
    }
    
    fileprivate struct Constants {
        static let accessToken = "access_token"
    }
    
    fileprivate let networkClient: NetworkClientProtocol
    
    init(networkClient: NetworkClientProtocol = NetworkClient.sharedInstance) {
        self.networkClient = networkClient
    }
    
    func fetchTweets(forUser user: String, completion: @escaping ([Tweet]?, Error?) -> ()) {
        checkAuthentication { (success, error) in
            if success {
                guard let url = TwitterAPIEndPoint.getLastestTweets(user).url else {
                    completion([], TweetListStoreError.invalidURL)
                    return
                }
                
                var request = URLRequest(url: url)
                request.httpMethod = TwitterAPIEndPoint.getLastestTweets(user).method
                _ = TwitterAPIEndPoint.getLastestTweets(user).headers.map { (key, value) in
                    request.addValue(value, forHTTPHeaderField: key)
                }
                
                self.networkClient.sendRequest(request: request) { (data, response, error) in
                    var tweets: [Tweet]?
                    var tweetError: Error?
                    
                    if let jsonArray = data?.jsonArray() {
                        tweets = jsonArray.flatMap { tweetDictionary -> Tweet? in
                            return Tweet.fromJSON(json: tweetDictionary)
                        }
                    } else {
                        tweetError = TweetListStoreError.invalidResponse
                    }
                    
                    completion(tweets, tweetError)
                }
            } else {
                completion([], error)
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
        guard let url = TwitterAPIEndPoint.getAccessToken.url else {
            completion(false, TweetListStoreError.invalidURL)
            return
        }
        
        var request = URLRequest(url: url)
        request.httpMethod = TwitterAPIEndPoint.getAccessToken.method
        request.httpBody = TwitterAPIEndPoint.getAccessToken.body
        
        _ = TwitterAPIEndPoint.getAccessToken.headers.map { (key, value) in
            request.addValue(value, forHTTPHeaderField: key)
        }
        
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
