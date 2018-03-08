//
//  AddHandlerTestsPresenterTests.swift
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

class AddHandlerTestsPresenterTests: XCTestCase {

	// MARK: - Variables

	private var router: MockRouter?
	private var interactor: MockInteractor?
	private var view: MockView?

	private var presenter: AddHandlerPresenter?

	// MARK: Test Functions

	func testInteractor() {
		presenter?.viewLoaded()
		presenter?.viewAppearing()
		presenter?.viewAppeared()
		presenter?.viewDisappeared()
		XCTAssertEqual(interactor?.titleRequested, true)
	}

	func testView() {
		presenter?.set(title: "AddHandlerTests")
		XCTAssertEqual(view?.title, "AddHandlerTests")
	}
    
    func testRouter() {
        presenter?.continueTapped()
        XCTAssertNotNil(router)
    }

	// MARK: Setup

	override func setUp() {
        super.setUp()
		let router = MockRouter()
		self.router = router
		let interactor = MockInteractor()
		self.interactor = interactor
		presenter = AddHandlerPresenter(router: router, interactor: interactor)
		view = MockView()
		presenter?.view = self.view
    }

    override func tearDown() {
        super.tearDown()
    }
}

// MARK: - Mock Classes

// MARK: -

private class MockRouter: RouterProtocol, AddHandlerPresenterRouterProtocol {
	// MARK: Variables

	var viewController: UIViewController?
}

// MARK: -

private class MockInteractor: AddHandlerPresenterInteractorProtocol {
	// MARK: Variables

	var titleRequested: Bool = false

	// MARK: Functions

	func requestTitle() {
		titleRequested = true
	}
}

// MARK: -

private class MockView: AddHandlerPresenterViewProtocol {
	// MARK: Variables

	var title: String?

	// MARK: Functions

	func set(title: String?) {
		self.title = title
	}
}
