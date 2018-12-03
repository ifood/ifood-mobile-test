//
//  SentimentFactory.swift
//  SentimentAnalysisTests
//
//  Created by Thales Frigo on 03/12/18.
//  Copyright © 2018 Thales Frigo. All rights reserved.
//

import Foundation
@testable import SentimentAnalysis

struct SentimentFactory {
    static func makeSentiment() -> Sentiment {
        return Sentiment(score: 0.6)
    }
}
