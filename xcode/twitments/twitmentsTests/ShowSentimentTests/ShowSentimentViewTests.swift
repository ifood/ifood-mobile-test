//
//  ShowSentimentViewTests.swift
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

class ShowSentimentViewTests: XCTestCase {

	// MARK: - Variables

	private var presenter: MockPresenter?
	private var view: ShowSentimentViewController?

	// MARK: Test Functions

	func testPresenter() {
		view?.beginAppearanceTransition(true, animated: false)
		view?.endAppearanceTransition()
		XCTAssertEqual(presenter?.isViewLoaded, true)

		view?.set(title: "ShowSentiment")
		XCTAssertEqual(view?.title, "ShowSentiment")

		view?.beginAppearanceTransition(false, animated: false)
		view?.endAppearanceTransition()
	}

	// MARK: Setup

	override func setUp() {
		super.setUp()
		let presenter = MockPresenter()
		self.presenter = presenter
		let storyboard: UIStoryboard = UIStoryboard(name: "Main", bundle: Bundle.main)
		view = storyboard.viewController(ShowSentimentViewController.self)
		view?.presenter = presenter
	}

	override func tearDown() {
		// Put teardown code here. This method is called after the invocation of each test method in the class.
		super.tearDown()
	}
}

// MARK: - Mock Classes

// MARK: -

private class MockPresenter: ShowSentimentViewPresenterProtocol {

	// MARK: Variables

	var isViewLoaded: Bool = false

	// MARK: Functions

	func viewLoaded() {
		isViewLoaded = true
	}
}
