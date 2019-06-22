//
//  TweetSentimentModels.swift
//  TwitterAnalyzer
//
//  Created by Bruno Vieira on 18/06/19.
//  Copyright © 2019 Bruno Vieira. All rights reserved.
//

import UIKit

enum TweetSentiment {
    // MARK: Use cases
    enum LoadInformation {
        struct Request {}
        
        struct Response {
            let sentiment: Tweet.Sentiment
        }
        
        struct ViewModel {
            let backgroundColor: UIColor
            let emoji: String
        }
    }
}
