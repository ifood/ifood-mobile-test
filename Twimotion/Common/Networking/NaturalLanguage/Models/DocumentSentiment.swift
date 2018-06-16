//
//  DocumentSentiment.swift
//  Twimotion
//
//  Created by Antony on 27/05/18.
//  Copyright © 2018 Twimotion. All rights reserved.
//

import Foundation

struct DocumentSentiment: Decodable {
    let score: Float
    let magnitude: Float
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
