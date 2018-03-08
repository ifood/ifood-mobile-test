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

/// Should be conformed to by the `AddHandlerPresenter` and referenced by `AddHandlerViewController`
protocol AddHandlerViewPresenterProtocol: ViewPresenterProtocol {
    func continueTapped()
}

/// Should be conformed to by the `AddHandlerPresenter` and referenced by `AddHandlerInteractor`
protocol AddHandlerInteractorPresenterProtocol: class {
	/** Sets the title for the presenter
	- parameters:
		- title The title to set
	*/
	func set(title: String?)
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
		interactor.requestTitle()
	}
    
    func continueTapped() {
        print("Go to next controller")
    }

	// MARK: - AddHandler Interactor to Presenter Protocol

	func set(title: String?) {
		view?.set(title: title)
	}
}
