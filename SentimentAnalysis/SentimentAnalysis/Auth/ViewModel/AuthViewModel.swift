//
//  AuthViewModel.swift
//  SentimentAnalysis
//
//  Created by Thales Frigo on 01/12/18.
//  Copyright © 2018 Thales Frigo. All rights reserved.
//

import Foundation

protocol AuthViewModelDelegate: AnyObject {
    func authenticateDidComplete(_ auth: Auth)
    func errorOnAuthenticate()
}

class AuthViewModel {
    
    let repository: AuthRepositoryType
    
    let authState: Variable<ViewModelState<Auth, Error>> = Variable(.empty)

    weak var delegate: AuthViewModelDelegate?
    
    init(repository: AuthRepositoryType) {
        self.repository = repository
    }
    
    func authenticate() {
        authState.value = .loading
        repository.authenticate { [weak self] (result) in
            switch result {
            case .success(let auth):
                self?.authState.value = .load(auth)
                self?.delegate?.authenticateDidComplete(auth)
            case .failure(let error):
                self?.authState.value = .error(error)
                self?.delegate?.errorOnAuthenticate()
            }
        }
    }
}
