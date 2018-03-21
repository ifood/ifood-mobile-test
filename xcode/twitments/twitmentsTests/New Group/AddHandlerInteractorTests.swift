//
//  AddHandlerInteractorTests.swift
//  Project: twitments
//
//  Module: AddHandler
//
//  By Ezequiel 08/03/18
//  Ezequiel 2018
//

// MARK: Imports

@testable import twitments

import SwiftyVIPER
import XCTest

// MARK: -

class AddHandlerInteractorTests: XCTestCase {

	// MARK: - Variables

	private var presenter: MockPresenter?
	private var interactor: AddHandlerInteractor?

	// MARK: Test Functions

	func testPresenter() {
		interactor?.authenticationStatus(true)
		XCTAssertEqual(presenter?.status, true)
	}

	// MARK: Setup

    override func setUp() {
        super.setUp()
		presenter = MockPresenter()
		interactor = AddHandlerInteractor()
		interactor?.presenter = presenter
    }

    override func tearDown() {
        super.tearDown()
    }
}

// MARK: - Mock Classes

// MARK: -

private class MockPresenter: AddHandlerInteractorPresenterProtocol {
    
    func authenticationStatus(_ status: Bool) {
        self.status = status
    }
    
    func reloadData(_ provider: AbstractDataProvider?, viewModels: [TwitterResultViewModel]) {
        
    }
    
    func reloadData(_ provider: AbstractDataProvider?, authentication: Bool) {
        
    }
    
    func errorData(_ provider: AbstractDataProvider?, error: NSError) {
        
    }
    

	// MARK: Variables

	var title: String?
    var status: Bool = false

	// MARK: Functions

	func set(title: String?) {
		self.title = title
	}
}
