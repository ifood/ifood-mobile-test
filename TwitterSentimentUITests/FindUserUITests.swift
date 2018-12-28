//
//  FindUserUITests.swift
//  TwitterSentimentUITests
//
//  Created by Jean Vinge on 28/12/18.
//  Copyright © 2018 Jean Vinge. All rights reserved.
//

import XCTest
import Resources

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
        
        let predicate = NSPredicate(format: "exists == 1")
        expectation(for: predicate, evaluatedWith: tableView, handler: nil)
        
        tableView.cells.element(boundBy: 5).tap()
        
        let sentimentCloseButton = app.label
        expectation(for: predicate, evaluatedWith: sentimentCloseButton, handler: nil)
        app.buttons["icon close x"].tap()
        
        let tweetCloseButton = app.navigationBars.firstMatch.buttons["icon close"]
        tweetCloseButton.tap()
        
        waitForExpectations(timeout: 3) { error in
            guard let error = error else { return }
            print(error.localizedDescription)
        }
    }

}
