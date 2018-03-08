//
//  AddHandlerTestsViewTests.swift
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

class AddHandlerTestsViewTests: XCTestCase {

	// MARK: - Variables

	private var presenter: MockPresenter?
	private var view: AddHandlerViewController?

	// MARK: Test Functions

	func testPresenter() {
		view?.beginAppearanceTransition(true, animated: false)
		view?.endAppearanceTransition()
		XCTAssertEqual(presenter?.isViewLoaded, true)

		view?.set(title: "AddHandlerTests")
		XCTAssertEqual(view?.title, "AddHandlerTests")
        
        view?.continueButton?.setTitle("ContinueTests", for: .normal)
        view?.continueTapped()
        XCTAssert(presenter?.continueTap ?? false)

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
    
    var continueTap: Bool = false

	// MARK: Functions

	func viewLoaded() {
		isViewLoaded = true
	}

    func continueTapped() {
        continueTap = true
    }
}
