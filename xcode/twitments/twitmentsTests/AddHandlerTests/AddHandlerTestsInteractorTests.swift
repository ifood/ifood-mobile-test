//
//  AddHandlerTestsInteractorTests.swift
//  Project: twitments
//
//  Module: AddHandlerTests
//
//  By Ezequiel 07/03/18
//  Ezequiel 2018
//

// MARK: Imports

@testable import twitments

import SwiftyVIPER
import XCTest

// MARK: -

class AddHandlerTestsInteractorTests: XCTestCase {

	// MARK: - Variables

	private var presenter: MockPresenter?
	private var interactor: AddHandlerInteractor?

	// MARK: Test Functions

	func testPresenter() {
		interactor?.requestTitle()
		XCTAssertEqual(presenter?.title, "AddHandler")
	}

	// MARK: Setup

    override func setUp() {
        super.setUp()
		presenter = MockPresenter()
		interactor = AddHandlerInteractor()
		interactor?.presenter = presenter
    }

    override func tearDown() {
        super.tearDown()
    }
}

// MARK: - Mock Classes

// MARK: -

private class MockPresenter: AddHandlerInteractorPresenterProtocol {

	// MARK: Variables

	var title: String?

	// MARK: Functions

	func set(title: String?) {
		self.title = title
	}
}
