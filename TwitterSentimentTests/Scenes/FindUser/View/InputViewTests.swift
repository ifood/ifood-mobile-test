//
//  InputTextViewTests.swift
//  TwitterSentimentTests
//
//  Created by Jean Vinge on 28/12/18.
//  Copyright © 2018 Jean Vinge. All rights reserved.
//

@testable import TwitterSentiment

import XCTest

class InputViewTests: XCTestCase {
    
    let view = InputView()

    func test_if_is_in_view() {
        XCTAssertTrue(view.sendButton.isDescendant(of: view))
        XCTAssertTrue(view.textField.isDescendant(of: view))
    }
}
