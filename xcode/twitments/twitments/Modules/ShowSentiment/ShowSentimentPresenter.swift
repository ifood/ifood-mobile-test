//
//  ShowSentimentPresenter.swift
//  Project: twitments
//
//  Module: ShowSentiment
//
//  By Ezequiel 08/03/18
//  Ezequiel 2018
//

// MARK: Imports

import UIKit

import SwiftyVIPER

// MARK: Protocols

/// Should be conformed to by the `ShowSentimentPresenter` and referenced by `ShowSentimentViewController`
protocol ShowSentimentViewPresenterProtocol: ViewPresenterProtocol {
    func closeSelected()
    func presentLoader()
}

/// Should be conformed to by the `ShowSentimentPresenter` and referenced by `ShowSentimentInteractor`
protocol ShowSentimentInteractorPresenterProtocol: class {
	/** Sets the title for the presenter
	- parameters:
		- title The title to set
	*/
	func set(title: String?)
}

// MARK: -

/// The Presenter for the ShowSentiment module
final class ShowSentimentPresenter: ShowSentimentViewPresenterProtocol, ShowSentimentInteractorPresenterProtocol {

	// MARK: - Constants

	let router: ShowSentimentPresenterRouterProtocol
	let interactor: ShowSentimentPresenterInteractorProtocol

	// MARK: Variables

	weak var view: ShowSentimentPresenterViewProtocol?

	// MARK: Inits

	init(router: ShowSentimentPresenterRouterProtocol, interactor: ShowSentimentPresenterInteractorProtocol) {
		self.router = router
		self.interactor = interactor
	}

	// MARK: - ShowSentiment View to Presenter Protocol

	func viewLoaded() {
		interactor.requestTitle()
	}
    
    func closeSelected() {
        router.hideLoader()
        router.dismiss(completion: nil)
    }
    
    func presentLoader() {
        router.presentLoader()
    }
    
	// MARK: - ShowSentiment Interactor to Presenter Protocol

	func set(title: String?) {
		view?.set(title: title)
	}
}
