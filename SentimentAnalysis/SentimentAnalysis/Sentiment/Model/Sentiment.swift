//
//  Sentiment.swift
//  SentimentAnalysis
//
//  Created by Thales Frigo on 01/12/18.
//  Copyright © 2018 Thales Frigo. All rights reserved.
//

import UIKit

enum Emotion {
    case happy
    case neutral
    case sad
    
    var color: UIColor {
        switch self {
        case .happy:
            return .green
        case .neutral:
            return .yellow
        case .sad:
            return .red
        }
    }
    
    var emoji: String {
        switch self {
        case .happy:
            return "😃"
        case .neutral:
            return "😐"
        case .sad:
            return "😔"
        }
    }
}

struct Sentiment: Codable {
    
    let score: Double
    
    var emotion: Emotion {
        get {
            switch score {
            case _ where score < -0.2:
                return .sad
            case _ where score < 0.5:
                return .neutral
            default:
                return .happy
            }
        }
    }
}

//{
//    "documentSentiment": {
//        "magnitude": 0.4,
//        "score": -0.4
//    },
//    "language": "en",
//    "sentences": [
//    {
//    "text": {
//    "content": "@Lascorbe @bontoJR @swiftalps @icanzilb Wish I could've been there - same as Luis, was impossible for me this year… https://t.co/unmFebDHJn",
//    "beginOffset": 0
//    },
//    "sentiment": {
//    "magnitude": 0.4,
//    "score": -0.4
//    }
//    }
//    ]
//}
