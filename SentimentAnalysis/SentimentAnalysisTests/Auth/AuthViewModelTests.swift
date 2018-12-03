//
//  AuthViewModelTests.swift
//  SentimentAnalysisTests
//
//  Created by Thales Frigo on 03/12/18.
//  Copyright © 2018 Thales Frigo. All rights reserved.
//

import XCTest
@testable import SentimentAnalysis

class AuthViewModelTests: XCTestCase {

    func testAuthSuccess() {
        // Given
        let repository = MockAuthRepository(isSuccess: true)
        let viewModel = AuthViewModel(repository: repository)
        let delegate = MockAuthViewModelDelegate()
        viewModel.delegate = delegate
        
        // When
        viewModel.authenticate()
        
        // Then
        XCTAssertTrue(delegate.hasAuthentication)
        XCTAssertFalse(delegate.hasError)
    }

    func testAuthFailure() {
        // Given
        let repository = MockAuthRepository(isSuccess: false)
        let viewModel = AuthViewModel(repository: repository)
        let delegate = MockAuthViewModelDelegate()
        viewModel.delegate = delegate
        
        // When
        viewModel.authenticate()
        
        // Then
        XCTAssertTrue(delegate.hasError)
        XCTAssertFalse(delegate.hasAuthentication)
    }
}

class MockAuthViewModelDelegate: AuthViewModelDelegate {
    
    var hasAuthentication = false
    var hasError = false
    
    func authenticateDidComplete(_ auth: Auth) {
        hasAuthentication = true
    }
    
    func errorOnAuthenticate() {
        hasError = true
    }
}
