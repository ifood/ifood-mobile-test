//
//  ShowSentimentInteractorTests.swift
//  Project: twitments
//
//  Module: ShowSentiment
//
//  By Ezequiel 08/03/18
//  Ezequiel 2018
//

// MARK: Imports

@testable import twitments

import SwiftyVIPER
import XCTest

// MARK: -

class ShowSentimentInteractorTests: XCTestCase {

	// MARK: - Variables

	private var presenter: MockPresenter?
	private var interactor: MockShowSentimentInteractor?

	// MARK: Test Functions

	func testPresenter() {
		interactor?.requestTitle()
        XCTAssertTrue((interactor?.titleRequested)!)
		XCTAssertEqual(presenter?.title, "ShowSentiment")
	}

	// MARK: Setup

    override func setUp() {
        super.setUp()
		// Put setup code here. This method is called before the invocation of each test method in the class.
		presenter = MockPresenter()
		interactor = MockShowSentimentInteractor()
		interactor?.presenter = presenter
    }

    override func tearDown() {
        // Put teardown code here. This method is called after the invocation of each test method in the class.
        super.tearDown()
    }
}

// MARK: - Mock Classes

// MARK: -

private class MockPresenter: ShowSentimentInteractorPresenterProtocol {

	// MARK: Variables

	var title: String?

	// MARK: Functions

	func set(title: String?) {
		self.title = title
	}
}

class MockShowSentimentInteractor: ShowSentimentPresenterInteractorProtocol {

    var titleRequested: Bool = false
    var avaliateSentiment: Bool = false
    var setDataProvider: Bool = false

    fileprivate var presenter: MockPresenter?
    // MARK: Functions

    func requestTitle() {
        titleRequested = true
        presenter?.set(title: "ShowSentiment")
    }

    func avaliateSentiment(_ message: String) {
        avaliateSentiment = true
    }

    func setDataProvider(_ dataProvider: ShowSentimentDataProvider) {
        setDataProvider = true
    }
}
