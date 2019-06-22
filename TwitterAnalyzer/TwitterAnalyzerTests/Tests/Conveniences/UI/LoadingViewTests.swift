//
//  LoadingViewTests.swift
//  TwitterAnalyzerTests
//
//  Created by Bruno Vieira on 21/06/19.
//  Copyright © 2019 Bruno Vieira. All rights reserved.
//

import XCTest
@testable import TwitterAnalyzer

class LoadingViewTests: XCTestCase {

    override func setUp() {
        super.setUp()
    }

    override func tearDown() {
        super.tearDown()
    }

    func testAddLoadingView() {
        // Given
        let view = UIView()
        // When
        LoadingView.addToView(view)
        // Then
        let loadingView = view.viewWithTag(-999)
        XCTAssertNotNil(loadingView, "addToView did not add a loadingView with tag -999 to the view.")
    }
    
    func testRemoveLoadingView() {
        // Given
        let view = UIView()
        LoadingView.addToView(view)
        // When
        LoadingView.removeFromView(view)
        // Then
        DispatchQueue.main.async {
            let loadingView = view.viewWithTag(-999)
            XCTAssertNil(loadingView, "removeFromView did not remove the loadingView with tag -999 from the view.")
        }
    }
}
