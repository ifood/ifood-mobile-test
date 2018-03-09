//
//  ListTweetsRouterTests.swift
//  Project: twitments
//
//  Module: ListTweets
//
//  By Ezequiel 08/03/18
//  Ezequiel 2018
//

// MARK: Imports

import XCTest

@testable import twitments

import SwiftyVIPER

// MARK: -

class ListTweetsRouterTests: XCTestCase {

	// MARK: - Variables

	private var router: ListTweetsRouter?

	// MARK: Setup

	override func setUp() {
        super.setUp()
		router = ListTweetsRouter()
    }

    override func tearDown() {
        super.tearDown()
    }
    
    func testRouter() {
        XCTAssertNotNil(router)
    }
}
