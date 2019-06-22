//
//  UserSearchPresenter.swift
//  TwitterAnalyzer
//
//  Created by Bruno Vieira on 18/06/19.
//  Copyright © 2019 Bruno Vieira. All rights reserved.
//

import UIKit

protocol UserSearchPresentationLogic {
    func presentAuthenticateTwitter(response: UserSearch.AuthenticateTwitter.Response)
    func presentError(response: UserSearch.Error.Response)
    func presentSearchUser(response: UserSearch.SearchUser.Response)
}

class UserSearchPresenter: UserSearchPresentationLogic {
    private weak var viewController: UserSearchDisplayLogic?
    
    init(viewController: UserSearchDisplayLogic) {
        self.viewController = viewController
    }
    
    // MARK: Authenticate Twitter
    
    func presentAuthenticateTwitter(response: UserSearch.AuthenticateTwitter.Response) {
        viewController?.displayAuthenticatTwitter(viewModel: UserSearch.AuthenticateTwitter.ViewModel())
    }
    
    // MARK: Search User
    
    func presentSearchUser(response: UserSearch.SearchUser.Response) {
        let viewModel = UserSearch.SearchUser.ViewModel()
        viewController?.displaySearchUser(viewModel: viewModel)
    }
    
    // MARK: Error
    
    func presentError(response: UserSearch.Error.Response) {
        viewController?.displayError(viewModel: UserSearch.Error.ViewModel(errorTitle: "Ops",
                                                                           errorMessage: response.error.description))
    }
}
