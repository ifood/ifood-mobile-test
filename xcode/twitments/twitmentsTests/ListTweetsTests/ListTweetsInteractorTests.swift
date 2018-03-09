//
//  ListTweetsInteractorTests.swift
//  Project: twitments
//
//  Module: ListTweets
//
//  By Ezequiel 08/03/18
//  Ezequiel 2018
//

// MARK: Imports

@testable import twitments

import SwiftyVIPER
import XCTest

// MARK: -

class ListTweetsInteractorTests: XCTestCase {

	// MARK: - Variables

	private var presenter: MockPresenter?
	private var interactor: ListTweetsInteractor?

	// MARK: Test Functions

	func testPresenter() {
		interactor?.requestTitle()
		XCTAssertEqual(presenter?.title, "ListTweets")
	}

	// MARK: Setup

    override func setUp() {
        super.setUp()
		// Put setup code here. This method is called before the invocation of each test method in the class.

		presenter = MockPresenter()

		interactor = ListTweetsInteractor()
		interactor?.presenter = presenter
    }

    override func tearDown() {
        // Put teardown code here. This method is called after the invocation of each test method in the class.
        super.tearDown()
    }
}

// MARK: - Mock Classes

// MARK: -

private class MockPresenter: ListTweetsInteractorPresenterProtocol {

	// MARK: Variables

	var title: String?

	// MARK: Functions

	func set(title: String?) {
		self.title = title
	}
}
