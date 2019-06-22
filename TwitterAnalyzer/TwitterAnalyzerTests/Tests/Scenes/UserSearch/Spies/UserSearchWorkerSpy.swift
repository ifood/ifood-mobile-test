//
//  UserSearchWorkerSpy.swift
//  TwitterAnalyzerTests
//
//  Created by Bruno Vieira on 21/06/19.
//  Copyright © 2019 Bruno Vieira. All rights reserved.
//

import UIKit
@testable import TwitterAnalyzer

class UserSearchWorkerSpy: UserSearchWorkerProtocol {
    
    enum SpyType {
        case success
        case failure
    }
    
    var type: SpyType
    var authenticateTwitterCalled = false
    var searchUserCalled = false
    
    init(_ type: SpyType) {
        self.type = type
    }
    
    func authenticateTwitter(success: @escaping (() -> Void), failure: @escaping ((ApplicationError) -> Void)) {
        authenticateTwitterCalled = true
        switch type {
        case .success:
            success()
        case .failure:
            failure(ApplicationError(.unknown))
        }
    }
    
    func searchUser(account: String, success: @escaping ((User) -> Void), failure: @escaping ((ApplicationError) -> Void)) {
        searchUserCalled = true
    }
}
