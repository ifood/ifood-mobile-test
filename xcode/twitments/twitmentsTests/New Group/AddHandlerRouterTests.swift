//
//  AddHandlerRouterTests.swift
//  Project: twitments
//
//  Module: AddHandler
//
//  By Ezequiel 08/03/18
//  Ezequiel 2018
//

// MARK: Imports

import XCTest

@testable import twitments

import SwiftyVIPER

// MARK: -

class AddHandlerRouterTests: XCTestCase {

	// MARK: - Variables

	private var router: AddHandlerRouter?

	// MARK: Setup

	override func setUp() {
        super.setUp()
		router = AddHandlerRouter()
    }

    override func tearDown() {
        super.tearDown()
    }
    
    func testRouter() {
        XCTAssertNotNil(router)
    }
}
