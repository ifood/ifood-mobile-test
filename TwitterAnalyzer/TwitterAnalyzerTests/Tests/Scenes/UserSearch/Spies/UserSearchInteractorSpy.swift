//
//  UserSearchInteractorSpy.swift
//  TwitterAnalyzerTests
//
//  Created by Bruno Vieira on 21/06/19.
//  Copyright © 2019 Bruno Vieira. All rights reserved.
//

import UIKit
@testable import TwitterAnalyzer

class UserSearchInteractorSpy: UserSearchBusinessLogic {
    var authenticateTwitterCalled = false
    var searchUserCalled = false
    
    func authenticateTwitter(request: UserSearch.AuthenticateTwitter.Request) {
        authenticateTwitterCalled = true
    }
    
    func searchUser(request: UserSearch.SearchUser.Request) {
        searchUserCalled = true
    }
}
