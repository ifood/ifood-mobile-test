//
//  SentimentTests.swift
//  SentimentAnalysisTests
//
//  Created by Thales Frigo on 03/12/18.
//  Copyright © 2018 Thales Frigo. All rights reserved.
//

import XCTest
@testable import SentimentAnalysis

class SentimentTests: XCTestCase {

    func testHappyFace() {
        let sentiment = Sentiment(score: 0.8)
        XCTAssertEqual(sentiment.emotion, Emotion.happy)
    }
    
    func testNeutralFace() {
        let sentiment = Sentiment(score: 0.4)
        XCTAssertEqual(sentiment.emotion, Emotion.neutral)
    }
    
    func testSadFace() {
        let sentiment = Sentiment(score: -0.3)
        XCTAssertEqual(sentiment.emotion, Emotion.sad)
    }
}
