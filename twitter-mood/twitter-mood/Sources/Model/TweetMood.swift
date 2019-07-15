//
//  TweetMood.swift
//  twitter-mood
//
//  Created by Dennis Merli on 7/7/19.
//  Copyright © 2019 Dennis Merli. All rights reserved.
//

import Foundation

struct TweetMood: Codable {
    var language: String?
    var mood: Mood?
    
    public enum CodingKeys: String, CodingKey {
        case language
        case mood = "documentSentiment"
    }
}

struct Mood: Codable {
    var magnitude: Double?
    var score: Double?
}
