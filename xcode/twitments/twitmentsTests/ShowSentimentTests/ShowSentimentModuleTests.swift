//
//  ShowSentimentModuleTests.swift
//  Project: twitments
//
//  Module: ShowSentiment
//
//  By Ezequiel 08/03/18
//  Ezequiel 2018
//

// MARK: Imports

@testable import twitments

import SwiftyVIPER
import XCTest

// MARK: -

class ShowSentimentModuleTests: XCTestCase {

	// MARK: - Variables

	var module: ShowSentimentModule?

	// MARK: Test Functions

	func testSetup() {
		XCTAssertNotNil(module)

		XCTAssertNotNil(module?.interactor)
		XCTAssertNotNil(module?.router)
		XCTAssertNotNil(module?.presenter)
		XCTAssertNotNil(module?.view)
		XCTAssertNotNil(module?.viewController)

		XCTAssertNotNil(module?.view.presenter)

		XCTAssertNotNil(module?.presenter.view)
		XCTAssertNotNil(module?.presenter.router)
		XCTAssertNotNil(module?.presenter.interactor)

		XCTAssertNotNil(module?.router.viewController)

		XCTAssertNotNil(module?.interactor.presenter)
	}

	// MARK: Setup

	override func setUp() {
		super.setUp()
		// Put setup code here. This method is called before the invocation of each test method in the class.

		module = ShowSentimentModule()
	}

	override func tearDown() {
		// Put teardown code here. This method is called after the invocation of each test method in the class.
		super.tearDown()
	}
}
