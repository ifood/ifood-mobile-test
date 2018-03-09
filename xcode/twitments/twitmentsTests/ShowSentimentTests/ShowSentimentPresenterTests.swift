//
//  ShowSentimentPresenterTests.swift
//  Project: twitments
//
//  Module: ShowSentiment
//
//  By Ezequiel 08/03/18
//  Ezequiel 2018
//

// MARK: Imports

import XCTest

@testable import twitments

import SwiftyVIPER

// MARK: -

class ShowSentimentPresenterTests: XCTestCase {

	// MARK: - Variables

	private var router: MockRouter?
	private var interactor: MockInteractor?
	private var view: MockView?

	private var presenter: ShowSentimentPresenter?

	// MARK: Test Functions

	func testInteractor() {
		presenter?.viewLoaded()
		presenter?.viewAppearing()
		presenter?.viewAppeared()
		presenter?.viewDisappeared()
		XCTAssertEqual(interactor?.titleRequested, true)
	}

	func testView() {
		presenter?.set(title: "ShowSentiment")
		XCTAssertEqual(view?.title, "ShowSentiment")
	}

	// MARK: Setup

	override func setUp() {
        super.setUp()
		// Put setup code here. This method is called before the invocation of each test method in the class.

		let router = MockRouter()
		self.router = router

		let interactor = MockInteractor()
		self.interactor = interactor

		presenter = ShowSentimentPresenter(router: router, interactor: interactor)

		view = MockView()
		presenter?.view = self.view
    }

    override func tearDown() {
        // Put teardown code here. This method is called after the invocation of each test method in the class.
        super.tearDown()
    }
}

// MARK: - Mock Classes

// MARK: -

private class MockRouter: RouterProtocol, ShowSentimentPresenterRouterProtocol {
	// MARK: Variables

	var viewController: UIViewController?
}

// MARK: -

private class MockInteractor: ShowSentimentPresenterInteractorProtocol {
	// MARK: Variables

	var titleRequested: Bool = false

	// MARK: Functions

	func requestTitle() {
		titleRequested = true
	}
}

// MARK: -

private class MockView: ShowSentimentPresenterViewProtocol {
	// MARK: Variables

	var title: String?

	// MARK: Functions

	func set(title: String?) {
		self.title = title
	}
}
