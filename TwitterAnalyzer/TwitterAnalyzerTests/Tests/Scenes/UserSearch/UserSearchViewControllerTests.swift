//
//  UserSearchViewControllerTests.swift
//  TwitterAnalyzerTests
//
//  Created by Bruno Vieira on 21/06/19.
//  Copyright © 2019 Bruno Vieira. All rights reserved.
//

import XCTest
@testable import TwitterAnalyzer

class UserSearchViewControllerTests: XCTestCase {

    var sut: UserSearchViewController!
    var interactorSpy: UserSearchInteractorSpy!
    
    override func setUp() {
        super.setUp()
        interactorSpy = UserSearchInteractorSpy()
        sut = UserSearchViewController()
        sut.interactor = interactorSpy
        sut.beginAppearanceTransition(true, animated: false)
        sut.endAppearanceTransition()
    }

    override func tearDown() {
        sut = nil
        interactorSpy = nil
    }
    
    func testAuthenticateTwitterCallWhenViewDidLoad() {
        // Given in setUp()
        // When
        sut.viewDidLoad()
        // Then
        XCTAssert(interactorSpy.authenticateTwitterCalled, "interactor.authenticateTwitter() should be called after view is loaded.")
    }
}
