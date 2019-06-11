//
//  SentimentAnalyzeModel.swift
//  IfoodMobileTest
//
//  Created by André Vieira on 10/06/19.
//  Copyright © 2019 André Vieira. All rights reserved.
//

import Foundation

struct DocumentSentimentModel: Codable {
    var magnitude: Double?
    var score: Double?
}

struct SentimentAnalyzeModel: Codable {
    var language: String?
    var sentiment: DocumentSentimentModel?
    
    public enum CodingKeys: String, CodingKey {
        case language
        case sentiment = "documentSentiment"
    }
}
