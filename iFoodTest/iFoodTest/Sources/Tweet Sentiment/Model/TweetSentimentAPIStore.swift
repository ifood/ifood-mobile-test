//
//  TweetSentimentAPIStore.swift
//  iFoodTest
//
//  Created by Mauricio Cesar Maniglia Junior on 09/06/18.
//  Copyright © 2018 Mauricio Cesar Maniglia Junior. All rights reserved.
//

import Foundation

final class TweetSentimentAPIStore: TweetSentimentStoreProtocol {
    enum TweetSentimentStoreError: Error {
        case generic
        case invalidURL
        case invalidResponse
    }
    
    fileprivate struct Constants {
        static let textSentiment = "documentSentiment"
    }
    
    fileprivate let networkClient: NetworkClientProtocol
    
    init(networkClient: NetworkClientProtocol = NetworkClient.sharedInstance) {
        self.networkClient = networkClient
    }
    
    func fetchSentimentAnalysis(forTweet tweet: Tweet, completion: @escaping (TextSentiment?, Error?) -> ()) {
        let request = GoogleNaturalLanguageAPIEndPoint.analyzeSentiment(text: tweet.text).urlRequest
        
        networkClient.sendRequest(request: request) { (data, response, error) in
            var textSentiment: TextSentiment?
            var textSentimentError: Error?
            
            if let jsonDictionary = data?.jsonDictionary(),
            let jsonDocumentSentiment = jsonDictionary[Constants.textSentiment] as? [String: Any] {
                textSentiment = TextSentiment.fromJSON(json: jsonDocumentSentiment)
            } else {
                textSentimentError = TweetSentimentStoreError.invalidResponse
            }
            
            completion(textSentiment, textSentimentError)
        }
    }
    
}
