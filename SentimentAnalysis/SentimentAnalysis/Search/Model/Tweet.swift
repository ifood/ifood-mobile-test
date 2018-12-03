//
//  Tweet.swift
//  SentimentAnalysis
//
//  Created by Thales Frigo on 28/11/18.
//  Copyright © 2018 Thales Frigo. All rights reserved.
//

import Foundation

struct Tweet: Codable {
    
    let id: Int64
    let createdAt: Date
    let text: String
    let user: User
    
    init(id: Int64, createdAt: Date, text: String, user: User) {
        self.id = id
        self.createdAt = createdAt
        self.text = text
        self.user = user
    }
    
    enum CodingKeys: String, CodingKey {
        case id
        case createdAt = "created_at"
        case text
        case user
    }
    
    init(from decoder: Decoder) throws {
        let container = try decoder.container(keyedBy: CodingKeys.self)

        id = try container.decode(Int64.self, forKey: .id)
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

extension Tweet: Equatable {
    static func == (lhs: Tweet, rhs: Tweet) -> Bool {
        return lhs.id == rhs.id
    }
}
