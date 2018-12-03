//
//  TwitterAPIConfig.swift
//  SentimentAnalysis
//
//  Created by Thales Frigo on 27/11/18.
//  Copyright © 2018 Thales Frigo. All rights reserved.
//

import Foundation
import Keys

struct TwitterAPIConfig {
    
    static let baseURL = URL(string: "https://api.twitter.com")!
    
    static func makeBasicAuthToken() -> String {
        let keys = SentimentAnalysisKeys()
        let basic = "\(keys.twitterConsumerKey):\(keys.twitterConsumerSecret)"
        guard let basicEncoded = basic.base64Encode else {
            return ""
        }
        
        return basicEncoded
    }
}
