//
//  AddHandlerModuleTests.swift
//  Project: twitments
//
//  Module: AddHandler
//
//  By Ezequiel 08/03/18
//  Ezequiel 2018
//

// MARK: Imports

@testable import twitments

import SwiftyVIPER
import XCTest

// MARK: -

class AddHandlerModuleTests: XCTestCase {

	// MARK: - Variables

	var module: AddHandlerModule?

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
		module = AddHandlerModule()
	}

	override func tearDown() {
		super.tearDown()
	}
}
