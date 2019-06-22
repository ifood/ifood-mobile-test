//
//  DateFormatter+ExtensionTests.swift
//  TwitterAnalyzerTests
//
//  Created by Bruno Vieira on 21/06/19.
//  Copyright © 2019 Bruno Vieira. All rights reserved.
//

import XCTest
@testable import TwitterAnalyzer

class DateFormatterExtensionTests: XCTestCase {
    
    override func setUp() {
        super.setUp()
    }
    
    override func tearDown() {
        super.tearDown()
    }
    
    func testStringToFullDate() {
        // Given
        let string = "Thu Apr 06 15:28:43 +0000 2017"
        // When
        let date = DateFormatter.fullDateFormatter().date(from: string)
        // Then
        XCTAssertNotNil(date, "fullDateFormatter did not return a valid Date object.")
    }
    
    func testDateToSimplifiedString() {
        // Given
        let date = DateFormatter().date(from: "")!
        // When
        let string = DateFormatter.simplifiedDateFormatter().string(from: date)
        // Then
        XCTAssertNotNil(string, "simplifiedDateFormatter did not return a valid String object.")
        XCTAssertEqual(string, "01/01/2000 00:00", "simplifiedDateFormatter should return '01/01/2000 00:00', but returned '\(string)'.")
    }
}
