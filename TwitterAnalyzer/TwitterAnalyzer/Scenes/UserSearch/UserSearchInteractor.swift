//
//  UserSearchInteractor.swift
//  TwitterAnalyzer
//
//  Created by Bruno Vieira on 18/06/19.
//  Copyright © 2019 Bruno Vieira. All rights reserved.
//

import UIKit

protocol UserSearchBusinessLogic {
    func authenticateTwitter(request: UserSearch.AuthenticateTwitter.Request)
    func searchUser(request: UserSearch.SearchUser.Request)
}

protocol UserSearchDataStore {
    var user: User? { get set }
}

class UserSearchInteractor: UserSearchBusinessLogic, UserSearchDataStore {
    var presenter: UserSearchPresentationLogic?
    var worker: UserSearchWorkerProtocol?
    var user: User?
    
    init(presenter: UserSearchPresentationLogic, worker: UserSearchWorkerProtocol) {
        self.presenter = presenter
        self.worker = worker
    }
    
    // MARK: Authenticate Twitter
    
    func authenticateTwitter(request: UserSearch.AuthenticateTwitter.Request) {
        worker?.authenticateTwitter(success: { [weak self] in
            self?.presenter?.presentAuthenticateTwitter(response: UserSearch.AuthenticateTwitter.Response())
        }, failure: { [weak self] (applicationError) in
            self?.presenter?.presentError(response: UserSearch.Error.Response(error: applicationError))
        })
    }
    
    // MARK: Search User
    
    func searchUser(request: UserSearch.SearchUser.Request) {
        worker?.searchUser(account: request.userAccount, success: { [weak self] (responseUser) in
            self?.user = responseUser
            self?.presenter?.presentSearchUser(response: UserSearch.SearchUser.Response(user: responseUser))
        }, failure: { [weak self]  (applicationError) in
            self?.presenter?.presentError(response: UserSearch.Error.Response(error: applicationError))
        })
    }
}
