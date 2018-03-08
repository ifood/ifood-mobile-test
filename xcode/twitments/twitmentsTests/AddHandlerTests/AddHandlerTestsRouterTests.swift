//
//  AddHandlerTestsRouterTests.swift
//  Project: twitments
//
//  Module: AddHandlerTests
//
//  By Ezequiel 07/03/18
//  Ezequiel 2018
//

// MARK: Imports

import XCTest

@testable import twitments

import SwiftyVIPER

// MARK: -

class AddHandlerTestsRouterTests: XCTestCase {

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
}
