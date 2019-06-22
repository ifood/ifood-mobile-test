//
//  UserSearchPresenterTests.swift
//  TwitterAnalyzerTests
//
//  Created by Bruno Vieira on 21/06/19.
//  Copyright © 2019 Bruno Vieira. All rights reserved.
//

import XCTest
@testable import TwitterAnalyzer

class UserSearchPresenterTests: XCTestCase {
    
    func testPresentAuthenticateUserCallsViewController() {
        // Given
        let userSearchViewControllerSpy = UserSearchViewControllerSpy()
        let sut = UserSearchPresenter(viewController: userSearchViewControllerSpy)
        // When
        sut.presentAuthenticateTwitter(response: UserSearch.AuthenticateTwitter.Response())
        // Then
        XCTAssert(userSearchViewControllerSpy.displayAuthenticateTwitterCalled, "presentAuthenticateUser() should ask the viewController to present the viewModel.")
    }
    
    func testPresentSearchUserCallsViewController() {
        // Given
        let userSearchViewControllerSpy = UserSearchViewControllerSpy()
        let sut = UserSearchPresenter(viewController: userSearchViewControllerSpy)
        // When
        sut.presentSearchUser(response: UserSearch.SearchUser.Response(user: User.mockedUser()))
        // Then
        XCTAssert(userSearchViewControllerSpy.displaySearchUserCalled, "presentSearchUser() should ask the viewController to present the viewModel.")
    }
    
    func testPresentErrorCallsViewController() {
        // Given
        let userSearchViewControllerSpy = UserSearchViewControllerSpy()
        let sut = UserSearchPresenter(viewController: userSearchViewControllerSpy)
        // When
        sut.presentError(response: UserSearch.Error.Response(error: ApplicationError(.userNotFound)))
        // Then
        XCTAssert(userSearchViewControllerSpy.displayErrorCalled, "presentError() should ask the viewController to present the viewModel.")
    }
}
