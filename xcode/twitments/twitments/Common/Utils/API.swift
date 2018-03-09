//
//  API.swift
//  twitments
//
//  Created by Ezequiel on 08/03/18.
//  Copyright © 2018 Ezequiel. All rights reserved.
//

import Foundation
struct API {    
    // MARK: Twitter
    static let twitterBaseURL = Configurations.shared.twitterBaseURL()
    static let userTimelinePath = "/1.1/statuses/user_timeline.json"
    static let authenticationPath = "/oauth2/token"
    
    // MARK: Google
    static let googleBaseURL = Configurations.shared.googleBaseURL()
    static let sentimentsPath = "/v1/documents:analyzeSentiment?fields=documentSentiment"
}

//import Foundation
//
///// Rules based on https://cloud.google.com/natural-language/docs/reference/rest/v1/Sentiment
//struct TweetSentimentBusiness {
//
//    func analyzeFeeling(sentiment: TweetSentimentAnalysis) -> TweetFeeling {
//        switch sentiment.magnitude {
//        case 0...0.099:
//            return .normal
//        default:
//            break
//        }
//        switch sentiment.score {
//        case 0:
//            return .normal
//        case let score where score < 0:
//            return .sad
//        case let score where score > 0:
//            return .happy
//        default:
//            //Couldn't analyze, returning normal status
//            return .normal
//        }
//    }
//
//}

