//
//  ListTweetsViewTests.swift
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

class ListTweetsViewTests: XCTestCase {

	// MARK: - Variables

	private var presenter: MockPresenter?
	private var view: ListTweetsViewController?

	// MARK: Test Functions

	func testPresenter() {
		view?.beginAppearanceTransition(true, animated: false)
		view?.endAppearanceTransition()
		XCTAssertEqual(presenter?.isViewLoaded, true)
		view?.set(title: "ListTweets")
		XCTAssertEqual(view?.title, "ListTweets")
		view?.beginAppearanceTransition(false, animated: false)
		view?.endAppearanceTransition()
	}

	// MARK: Setup

	override func setUp() {
		super.setUp()
		let presenter = MockPresenter()
		self.presenter = presenter
		let storyboard: UIStoryboard = UIStoryboard(name: "Main", bundle: Bundle.main)
		view = storyboard.viewController(ListTweetsViewController.self)
		view?.presenter = presenter
		guard view == nil else { return }
	}

	override func tearDown() {
		super.tearDown()
	}
}

// MARK: - Mock Classes

// MARK: -

private class MockPresenter: ListTweetsViewPresenterProtocol {

	// MARK: Variables

	var isViewLoaded: Bool = false
    var isClosed: Bool = false
    var isShowSentiment: Bool = false
	// MARK: Functions

    func showSentiment(_ to: TwitterResultViewModel) {
        isShowSentiment = true
    }

	func viewLoaded() {
		isViewLoaded = true
	}

    func closeSelected() {
        isClosed = true
    }
}
