//
//  TwimotionUITests.swift
//  TwimotionUITests
//
//  Created by Antony on 30/05/18.
//  Copyright © 2018 Twimotion. All rights reserved.
//

import XCTest

class TwimotionUITests: XCTestCase {
        
    override func setUp() {
        super.setUp()
        // In UI tests it is usually best to stop immediately when a failure occurs.
        continueAfterFailure = false
        // UI tests must launch the application that they test. Doing this in setup will make sure it happens for each test method.
        XCUIApplication().launch()
    }
    
    func testAppFlow() {
        
        let app = XCUIApplication()
        app.textFields["@username"].tap()
        app.textFields["@username"].typeText("elonmusk")
        app.buttons["List tweets"].tap()
        
        let tablesQuery = app.tables
        
        tablesQuery.cells.element(boundBy: 0).tap() // tap first cell
        app.buttons["close"].tap()
        
        tablesQuery.cells.element(boundBy: 1).tap() // tap second cell
        app.buttons["close"].tap()
        
        app.navigationBars["@elonmusk"].buttons["Back"].tap()
    }
    
}
