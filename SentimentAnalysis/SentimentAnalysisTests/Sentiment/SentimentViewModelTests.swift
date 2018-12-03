//
//  SentimentViewModelTests.swift
//  SentimentAnalysisTests
//
//  Created by Thales Frigo on 03/12/18.
//  Copyright © 2018 Thales Frigo. All rights reserved.
//

import XCTest
@testable import SentimentAnalysis

class SentimentViewModelTests: XCTestCase {
    
    func testSentimentSuccess() {
        let repository = MockSentimentRepository(isSuccess: true)
        let viewModel = SentimentViewModel(tweet: TweetFactory.makeTweet(), repository: repository)
        
        let expectation = self.expectation(description: "SentimentRepository Success")
        viewModel.state.onUpdate = { state in
            switch state {
            case .empty:
                XCTAssertTrue(true)
            case .loading:
                XCTAssertTrue(true)
            case .load:
                XCTAssertTrue(true)
                expectation.fulfill()
            default:
                XCTFail("Fail on get sentiment")
            }
        }
        
        viewModel.analyzeDocument()
        waitForExpectations(timeout: Timeout.value, handler: nil)
    }
    
    func testSentimentFailure() {
        let repository = MockSentimentRepository(isSuccess: false)
        let viewModel = SentimentViewModel(tweet: TweetFactory.makeTweet(), repository: repository)
        
        let expectation = self.expectation(description: "SentimentRepository failure")
        viewModel.state.onUpdate = { state in
            switch state {
            case .empty:
                XCTAssertTrue(true)
            case .loading:
                XCTAssertTrue(true)
            case .error:
                XCTAssertTrue(true)
                expectation.fulfill()
            default:
                XCTFail("Error dont called")
            }
        }
        
        viewModel.analyzeDocument()
        waitForExpectations(timeout: Timeout.value, handler: nil)
    }
}
