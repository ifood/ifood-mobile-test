//
//  FindUserViewTests.swift
//  TwitterSentimentTests
//
//  Created by Jean Vinge on 28/12/18.
//  Copyright © 2018 Jean Vinge. All rights reserved.
//

@testable import TwitterSentiment

import XCTest

class FindUserViewTests: XCTestCase {
    
    var view = FindUserView()

    func test_if_is_in_on_screen() {
        XCTAssertTrue(view.inputTextView.isDescendant(of: view))
        XCTAssertTrue(view.logoImageView.isDescendant(of: view))
        XCTAssertTrue(view.titleLabel.isDescendant(of: view))
        XCTAssertTrue(view.viewContainer.isDescendant(of: view))
    }
}
