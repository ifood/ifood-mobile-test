//
//  ListTweetsPresenterTests.swift
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

class ListTweetsPresenterTests: XCTestCase {

	// MARK: - Variables

	private var router: MockRouter?
	private var interactor: MockInteractor?
	private var view: MockView?

	private var presenter: ListTweetsPresenter?

	// MARK: Test Functions

	func testInteractor() {
		presenter?.viewLoaded()
		presenter?.viewAppearing()
		presenter?.viewAppeared()
		presenter?.viewDisappeared()
		XCTAssertEqual(interactor?.titleRequested, true)
	}

	func testView() {
		presenter?.set(title: "ListTweets")
		XCTAssertEqual(view?.title, "ListTweets")
	}

	// MARK: Setup

	override func setUp() {
        super.setUp()
		// Put setup code here. This method is called before the invocation of each test method in the class.

		let router = MockRouter()
		self.router = router

		let interactor = MockInteractor()
		self.interactor = interactor

		presenter = ListTweetsPresenter(router: router, interactor: interactor)

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

private class MockRouter: RouterProtocol, ListTweetsPresenterRouterProtocol {
    
	// MARK: Variables
	var viewController: UIViewController?
    
    func present(_ to: TwitterResultViewModel) {
        ShowSentimentModule().present(from: viewController, style: .coverVertical)
    }
}

// MARK: -

private class MockInteractor: ListTweetsPresenterInteractorProtocol {
	// MARK: Variables

	var titleRequested: Bool = false

	// MARK: Functions

	func requestTitle() {
		titleRequested = true
	}
}

// MARK: -

private class MockView: ListTweetsPresenterViewProtocol {
	// MARK: Variables

	var title: String?

	// MARK: Functions

	func set(title: String?) {
		self.title = title
	}
}
