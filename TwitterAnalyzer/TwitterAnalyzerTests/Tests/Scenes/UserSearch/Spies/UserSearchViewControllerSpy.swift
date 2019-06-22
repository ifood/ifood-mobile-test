//
//  UserSearchViewControllerSpy.swift
//  TwitterAnalyzerTests
//
//  Created by Bruno Vieira on 21/06/19.
//  Copyright © 2019 Bruno Vieira. All rights reserved.
//

import UIKit
@testable import TwitterAnalyzer

class UserSearchViewControllerSpy: UserSearchDisplayLogic {
    
    var displayAuthenticateTwitterCalled = false
    var displaySearchUserCalled = false
    var displayErrorCalled = false
    
    func displayAuthenticatTwitter(viewModel: UserSearch.AuthenticateTwitter.ViewModel) {
        displayAuthenticateTwitterCalled = true
    }
    
    func displaySearchUser(viewModel: UserSearch.SearchUser.ViewModel) {
        displaySearchUserCalled = true
    }
    
    func displayError(viewModel: UserSearch.Error.ViewModel) {
        displayErrorCalled = true
    }
}
