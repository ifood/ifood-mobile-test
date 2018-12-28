//
//  TweetListViewTests.swift
//  TwitterSentimentTests
//
//  Created by Jean Vinge on 28/12/18.
//  Copyright © 2018 Jean Vinge. All rights reserved.
//

@testable import TwitterSentiment

import XCTest

class TweetListViewTests: XCTestCase {
    
    let view = TweetListView()

    func test_if_is_in_view() {
        XCTAssertTrue(view.tableView.isDescendant(of: view))
        XCTAssertTrue(view.tableView.refresh.isDescendant(of: view.tableView))
    }

}
