//
//  AddHandlerInteractor.swift
//  Project: twitments
//
//  Module: AddHandler
//
//  By Ezequiel 07/03/18
//  Ezequiel 2018
//

// MARK: Imports

import Foundation
import SwiftyVIPER

// MARK: Protocols

/// Should be conformed to by the `AddHandlerInteractor` and referenced by `AddHandlerPresenter`
protocol AddHandlerPresenterInteractorProtocol {
    func setDataProvider(_ dataProvider: AddHandlerDataProvider)
    func fetchUserTwittes(_ userName: String)
    func authentication()
    func authenticationStatus(_ status: Bool)
}

// MARK: -

/// The Interactor for the AddHandler module
final class AddHandlerInteractor: AddHandlerPresenterInteractorProtocol {

	// MARK: - Variables

	weak var presenter: AddHandlerInteractorPresenterProtocol?
    fileprivate var manager: AddHandlerDataProviderManager?

	// MARK: - AddHandler Presenter to Interactor Protocol

    func setDataProvider(_ dataProvider: AddHandlerDataProvider) {
        manager = AddHandlerDataProviderManager()
        manager?.dataProvider = dataProvider
    }

    func fetchUserTwittes(_ userName: String) {
        manager?.fetchUserTwittes(userName)
    }

    func authentication() {
        manager?.authenticate()
    }

    func authenticationStatus(_ status: Bool) {
        presenter?.authenticationStatus(status)
    }
}
