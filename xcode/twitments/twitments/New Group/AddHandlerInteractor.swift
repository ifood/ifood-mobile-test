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
	/// Requests the title for the presenter
	func requestTitle()
}

// MARK: -

/// The Interactor for the AddHandler module
final class AddHandlerInteractor: AddHandlerPresenterInteractorProtocol {

	// MARK: - Variables

	weak var presenter: AddHandlerInteractorPresenterProtocol?

	// MARK: - AddHandler Presenter to Interactor Protocol

	func requestTitle() {
		presenter?.set(title: "AddHandler")
	}
}
