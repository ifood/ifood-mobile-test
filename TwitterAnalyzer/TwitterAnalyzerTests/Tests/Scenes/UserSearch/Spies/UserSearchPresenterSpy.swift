//
//  UserSearchPresenterSpy.swift
//  TwitterAnalyzerTests
//
//  Created by Bruno Vieira on 21/06/19.
//  Copyright © 2019 Bruno Vieira. All rights reserved.
//

import UIKit
@testable import TwitterAnalyzer

class UserSearchPresenterSpy: UserSearchPresentationLogic {
    
    var presentAuthenticateTwitterCalled = false
    var presentSearchUserCalled = false
    var presentErrorCalled = false
    
    func presentAuthenticateTwitter(response: UserSearch.AuthenticateTwitter.Response) {
        presentAuthenticateTwitterCalled = true
    }
    
    func presentError(response: UserSearch.Error.Response) {
        presentErrorCalled = true
    }
    
    func presentSearchUser(response: UserSearch.SearchUser.Response) {
        presentSearchUserCalled = true
    }
}
