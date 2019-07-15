//
//  Sentiment.swift
//  TwitterSentiments
//
//  Created by Marina Azevedo on 14/07/19.
//  Copyright © 2019 Marina Azevedo. All rights reserved.
//

import Foundation

struct DocumentSentiment: Decodable {
    let score: Float
}

enum Sentiment {
    case happy
    case neutral
    case sad
}

extension DocumentSentiment {
    
    var sentiment: Sentiment {
        switch score {
        case let score where score < 0:
            return .sad
        case let score where score > 0:
            return .happy
        default:
            return .neutral
        }
    }
    
}

struct DocumentSentimentResponse: Decodable {
    let documentSentiment: DocumentSentiment
    let language: String
}
