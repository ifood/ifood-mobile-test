//
//  DocumentSentiment.swift
//  ifood-mobile-test
//
//  Created by Jean Vinge on 26/12/18.
//  Copyright © 2018 Jean Vinge. All rights reserved.
//

import Foundation

public struct DocumentSentiment: Codable {

    // MARK: Var

    public let score: Float
    public let magnitude: Float

    public var sentiment: Sentiment {
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
