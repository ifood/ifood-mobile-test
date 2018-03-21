//
//  AddHandlerPresenterTests.swift
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

class AddHandlerPresenterTests: XCTestCase {

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
        presenter?.authentication()
        presenter?.authenticationStatus(true)
        presenter?.continueTapped("ifood")
		XCTAssertEqual(interactor?.setDataProvider, true)
        XCTAssertTrue((interactor?.fetchUserTwittes)!)
	}

	func testView() {
        presenter?.authenticationStatus(true)
        XCTAssertTrue((view?.status)!)
		//presenter?.set(title: "AddHandler")
		//XCTAssertEqual(view?.title, "AddHandler")
	}

	// MARK: Setup

	override func setUp() {
        super.setUp()
		// Put setup code here. This method is called before the invocation of each test method in the class.

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

    var isPresented = false
    var isShowingLoader = false

    func present(_ list: [TwitterResultViewModel]) {
        self.isPresented = true
    }

    func presentLoader() {
        self.isShowingLoader = true
    }

    func hideLoader() {
        self.isShowingLoader = false
    }

	// MARK: Variables

	var viewController: UIViewController?
}

// MARK: -

private class MockInteractor: AddHandlerPresenterInteractorProtocol {

	// MARK: Variables

	var titleRequested: Bool = false
    var setDataProvider: Bool = false
    var fetchUserTwittes: Bool = false
    var isAuthentication: Bool = false
    var authenticationStatus: Bool = false
	// MARK: Functions

	func requestTitle() {
		titleRequested = true
	}

    func setDataProvider(_ dataProvider: AddHandlerDataProvider) {
        self.setDataProvider = true
    }

    func fetchUserTwittes(_ userName: String) {
        self.fetchUserTwittes = true
    }

    func authentication() {
        self.isAuthentication = true
    }

    func authenticationStatus(_ status: Bool) {
        self.authenticationStatus = true
    }
}

// MARK: -

private class MockView: AddHandlerPresenterViewProtocol {

	// MARK: Variables

	var title: String?
    var status: Bool?
	// MARK: Functions

	func set(title: String?) {
		self.title = title
	}

    func authenticationStatus(_ status: Bool) {
        self.status = status
    }
}
