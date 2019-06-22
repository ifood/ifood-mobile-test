//
//  UserSearchInteractorTests.swift
//  TwitterAnalyzerTests
//
//  Created by Bruno Vieira on 21/06/19.
//  Copyright © 2019 Bruno Vieira. All rights reserved.
//

import XCTest
@testable import TwitterAnalyzer

class UserSearchInteractorTests: XCTestCase {
    
    func testAuthenticateTwitterCallsWorker() {
        // Given
        let userSearchWorkerSpy = UserSearchWorkerSpy(.success)
        let sut = UserSearchInteractor(presenter: UserSearchPresenterSpy(), worker: userSearchWorkerSpy)
        // When
        sut.authenticateTwitter(request: UserSearch.AuthenticateTwitter.Request())
        // Then
        XCTAssert(userSearchWorkerSpy.authenticateTwitterCalled, "authenticateTwitter() should ask the worker to autenticate twitter tokens.")
    }
    
    func testAuthenticateTwitterSuccessCallsPresenter() {
        // Given
        let userSearchPresenterSpy = UserSearchPresenterSpy()
        let userSearchWorkerSpy = UserSearchWorkerSpy(.success)
        let sut = UserSearchInteractor(presenter: userSearchPresenterSpy, worker: userSearchWorkerSpy)
        // When
        sut.authenticateTwitter(request: UserSearch.AuthenticateTwitter.Request())
        // Then
        XCTAssert(userSearchPresenterSpy.presentAuthenticateTwitterCalled, "authentcateTwitter() should ask the presenter to present the response.")
    }
    
    func testAuthenticateTwitterFailureCallsPresenter() {
        // Given
        let userSearchPresenterSpy = UserSearchPresenterSpy()
        let userSearchWorkerSpy = UserSearchWorkerSpy(.failure)
        let sut = UserSearchInteractor(presenter: userSearchPresenterSpy, worker: userSearchWorkerSpy)
        // When
        sut.authenticateTwitter(request: UserSearch.AuthenticateTwitter.Request())
        // Then
        XCTAssert(userSearchPresenterSpy.presentErrorCalled, "authentcateTwitter() should ask the presenter to present the error.")
    }
    
}
