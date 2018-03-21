//
//  AddHandlerViewTests.swift
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

class AddHandlerViewTests: XCTestCase {

	// MARK: - Variables

	private var presenter: MockPresenter?
	private var view: AddHandlerViewController?

	// MARK: Test Functions

	func testPresenter() {
		view?.beginAppearanceTransition(true, animated: false)
		view?.endAppearanceTransition()
		XCTAssertEqual(presenter?.isViewLoaded, true)
        view?.authenticationStatus(true)
        XCTAssertTrue((presenter?.isAuthentication)!)
//        view?.set(title: "AddHandler")
//        XCTAssertEqual(view?.title, "AddHandler")

		view?.beginAppearanceTransition(false, animated: false)
		view?.endAppearanceTransition()
	}

	// MARK: Setup

	override func setUp() {
		super.setUp()
		let presenter = MockPresenter()
		self.presenter = presenter
		let storyboard: UIStoryboard = UIStoryboard(name: "Main", bundle: Bundle.main)
		view = storyboard.viewController(AddHandlerViewController.self)
        view?.presenter = presenter
	}

	override func tearDown() {
		super.tearDown()
	}
}

// MARK: - Mock Classes

// MARK: -

private class MockPresenter: AddHandlerViewPresenterProtocol {

    
    // MARK: Variables
    var isViewLoaded: Bool = false
    var isContinueTapped: Bool = false
    var isAuthentication: Bool = false
    var userName: String = ""
    
	// MARK: Functions

	func viewLoaded() {
		isViewLoaded = true
	}
    
    func continueTapped(_ userName: String) {
        isContinueTapped = true
        self.userName = userName
    }
    
    
    func authentication() {
        isAuthentication = true
    }
}
