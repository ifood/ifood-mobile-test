//
//  Tweet.swift
//  TwitterAnalyzer
//
//  Created by Bruno Vieira on 18/06/19.
//  Copyright © 2019 Bruno Vieira. All rights reserved.
//

import Foundation

class Tweet: Codable {
    enum Sentiment {
        case NEGATIVE
        case NEUTRAL
        case POSITIVE
    }
    
    enum CodingKeys: String, CodingKey {
        case text = "full_text"
        case date = "created_at"
        case id = "id_str"
        case replyAccount = "in_reply_to_screen_name"
        case retweet = "retweeted_status"
        case user = "user"
    }
    
    struct TweetUser: Codable {
        let account: String?
        
        enum CodingKeys: String, CodingKey {
            case account = "screen_name"
        }
    }
    
    let text: String?
    let date: String?
    let id: String?
    let replyAccount: String?
    let retweet: Tweet?
    let user: TweetUser?
    var sentiment: SentimentAnalysis?
}
