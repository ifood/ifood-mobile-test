//
//  TextSentiment.swift
//  iFoodTest
//
//  Created by Mauricio Cesar Maniglia Junior on 09/06/18.
//  Copyright © 2018 Mauricio Cesar Maniglia Junior. All rights reserved.
//

import Foundation

enum Sentiment {
    case happy
    case sad
    case neutral
}

struct TextSentiment {
    let score: Float
    
    var sentiment: Sentiment {
        switch score {
        case let score where score > 0:
            return .happy
        case let score where score < 0:
            return .sad
        default:
            return .neutral
        }
    }
}

extension TextSentiment: Parsable {
    
    private struct Constants {
        static let scoreKey = "score"
    }
    
    static func fromJSON(json: [String: Any]) -> TextSentiment? {
        if let score = json[Constants.scoreKey] as? NSNumber {
            return TextSentiment(score: score.floatValue)
        }
        
        return nil
    }
}
