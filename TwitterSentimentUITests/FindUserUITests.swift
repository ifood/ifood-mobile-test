//
//  FindUserUITests.swift
//  TwitterSentimentUITests
//
//  Created by Jean Vinge on 28/12/18.
//  Copyright © 2018 Jean Vinge. All rights reserved.
//

import XCTest
import Resources

func exists(shouldExist: Bool) -> NSPredicate {
    return NSPredicate(format: "exists == \(shouldExist == true ? 1 : 0)")
}

class FindUserUITests: XCTestCase {

    override func setUp() {
        continueAfterFailure = false
        XCUIApplication().launch()
    }

    func test_features_on_open() {
        
        let app = XCUIApplication()
        
        app.textFields[L10n.FindUser.textfieldPlaceholder].typeText("uol")
        app.buttons["icon search"].tap()
        
        let tableView = app.tables
        let tableExpec = expectation(for: exists(shouldExist: true), evaluatedWith: tableView, handler: nil)
        tableExpec.isInverted = true
        tableView.cells.element(boundBy: 5).tap()

        let activityExpec = expectation(for: exists(shouldExist: true), evaluatedWith: app.activityIndicators, handler: nil)
        activityExpec.isInverted = true
        app.buttons["icon close x"].tap()

        let tweetCloseButton = app.navigationBars.firstMatch.buttons["icon close"]
        tweetCloseButton.tap()
        waitForExpectations(timeout: 2, handler: nil)
    }

}
