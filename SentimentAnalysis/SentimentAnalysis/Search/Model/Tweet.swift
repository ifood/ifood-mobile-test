//
//  Tweet.swift
//  SentimentAnalysis
//
//  Created by Thales Frigo on 28/11/18.
//  Copyright © 2018 Thales Frigo. All rights reserved.
//

import Foundation

struct Tweet: Codable {
    
    let createdAt: Date
    let text: String
    let user: User
    
    init(createdAt: Date, text: String, user: User) {
        self.createdAt = createdAt
        self.text = text
        self.user = user
    }
    
    enum CodingKeys: String, CodingKey {
        case createdAt = "created_at"
        case text
        case user
    }
    
    init(from decoder: Decoder) throws {
        let container = try decoder.container(keyedBy: CodingKeys.self)
        
        text = try container.decode(String.self, forKey: .text)
        user = try container.decode(User.self, forKey: .user)
        
        let createdAtString = try container.decode(String.self, forKey: .createdAt)
        let formatter = DateFormatter.twitterDateFormat
        if let date = formatter.date(from: createdAtString) {
            createdAt = date
        } else {
            throw DecodingError.dataCorruptedError(
                forKey: .createdAt,
                in: container,
                debugDescription: "Date string does not match format expected by formatter."
            )
        }
    }
}
