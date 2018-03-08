//
//  AddHandlerPresenter.swift
//  Project: twitments
//
//  Module: AddHandler
//
//  By Ezequiel 07/03/18
//  Ezequiel 2018
//

// MARK: Imports

import UIKit

import SwiftyVIPER

// MARK: Protocols

protocol AddHandlerViewPresenterProtocol: ViewPresenterProtocol {
    func continueTapped(_ userName:String)
    func authentication()
}

protocol AddHandlerInteractorPresenterProtocol: AddHandlerDataProvider {
    func authenticationStatus(_ status:Bool)
}

// MARK: -

/// The Presenter for the AddHandler module
final class AddHandlerPresenter: AddHandlerViewPresenterProtocol, AddHandlerInteractorPresenterProtocol {

	// MARK: - Constants

	let router: AddHandlerPresenterRouterProtocol
	let interactor: AddHandlerPresenterInteractorProtocol

	// MARK: Variables

	weak var view: AddHandlerPresenterViewProtocol?

	// MARK: Inits

	init(router: AddHandlerPresenterRouterProtocol, interactor: AddHandlerPresenterInteractorProtocol) {
		self.router = router
		self.interactor = interactor
	}

	// MARK: - AddHandler View to Presenter Protocol

	func viewLoaded() {
        interactor.setDataProvider(self)
	}
    
    func continueTapped(_ userName: String) {
        print("Go to next controller")
        interactor.fetchUserTwittes(userName)
    }
    
    func gotoTwittesList(_ viewModels:[TwitterResultViewModel]) {
        router.present(viewModels)
    }
    
    func authentication() {
        interactor.authentication()
    }

	// MARK: - AddHandler Interactor to Presenter Protocol / DataProvider Protocol
    
    func authenticationStatus(_ status: Bool) {
        view?.authenticationStatus(status)
    }
    
    func reloadData(_ provider: AbstractDataProvider?, viewModels: [TwitterResultViewModel]) {
        print("VIEW MODELS")
        print(viewModels.count)
        AlertMessageView().stop()
        self.gotoTwittesList(viewModels)
    }
    
    func reloadData(_ provider: AbstractDataProvider?, authentication: Bool) {
        print("AUTHENTICADO MANO")
        interactor.authenticationStatus(authentication)
    }
    
    func errorData(_ provider: AbstractDataProvider?, error: NSError) {
        
    }
}
