//
//  SentimentAnalysisRestApi.swift
//  ifood-devtest
//
//  Created by Rafael Zilião on 30/11/18.
//  Copyright © 2018 Rafael Zilião. All rights reserved.
//

import Foundation

protocol SentimentAnalysisService {
    func requestSentimentAnalysisTweet(tweet: String,
                                       success: @escaping (Sentiment) -> (),
                                       failure: @escaping (Error) -> ())
    
}

class SentimentAnalysisRestApi: SentimentAnalysisService {
    
    func requestSentimentAnalysisTweet(tweet: String,
                                       success: @escaping (Sentiment) -> (),
                                       failure: @escaping (Error) -> ()) {
        
        Sentiment.requestSentimentAnalysisTweet(tweet: tweet, success: { (sentiment) in
            success(sentiment)
        }){ (error) in
            failure(error)
        }
    }
    
    
}
