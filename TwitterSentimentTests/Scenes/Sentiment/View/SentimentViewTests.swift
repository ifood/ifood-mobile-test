//
//  SentimentViewTests.swift
//  TwitterSentimentTests
//
//  Created by Jean Vinge on 28/12/18.
//  Copyright © 2018 Jean Vinge. All rights reserved.
//

@testable import TwitterSentiment

import XCTest

class SentimentViewTests: XCTestCase {
    
    var view = SentimentView()

    func testExample() {
        XCTAssertTrue(view.background.isDescendant(of: view))
        XCTAssertTrue(view.closeButton.isDescendant(of: view))
        XCTAssertTrue(view.container.isDescendant(of: view))
        XCTAssertTrue(view.indicator.isDescendant(of: view.container))
        XCTAssertTrue(view.emojiLabel.isDescendant(of: view.container))
        XCTAssertTrue(view.titleLabel.isDescendant(of: view.container))
    }
}
