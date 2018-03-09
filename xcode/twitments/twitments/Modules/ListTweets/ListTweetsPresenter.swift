//
//  ListTweetsPresenter.swift
//  Project: twitments
//
//  Module: ListTweets
//
//  By Ezequiel 08/03/18
//  Ezequiel 2018
//

// MARK: Imports

import UIKit

import SwiftyVIPER

// MARK: Protocols

protocol ListTweetsViewPresenterProtocol: ViewPresenterProtocol {
    func closeSelected()
    func showSentiment(_ to:TwitterResultViewModel)
}

/// Should be conformed to by the `ListTweetsPresenter` and referenced by `ListTweetsInteractor`
protocol ListTweetsInteractorPresenterProtocol: class {
	/** Sets the title for the presenter
	- parameters:
		- title The title to set
	*/
	func set(title: String?)
}

// MARK: -

/// The Presenter for the ListTweets module
final class ListTweetsPresenter: ListTweetsViewPresenterProtocol, ListTweetsInteractorPresenterProtocol {

	// MARK: - Constants

	let router: ListTweetsPresenterRouterProtocol
	let interactor: ListTweetsPresenterInteractorProtocol

	// MARK: Variables

	weak var view: ListTweetsPresenterViewProtocol?

	// MARK: Inits

	init(router: ListTweetsPresenterRouterProtocol, interactor: ListTweetsPresenterInteractorProtocol) {
		self.router = router
		self.interactor = interactor
	}

	// MARK: - ListTweets View to Presenter Protocol

	func viewLoaded() {
		interactor.requestTitle()
	}
    
    func closeSelected() {
        router.hideLoader()
        router.dismiss(completion: nil)
    }
    
    func showSentiment(_ to: TwitterResultViewModel) {
        router.present(to)
    }
    

	// MARK: - ListTweets Interactor to Presenter Protocol

	func set(title: String?) {
		view?.set(title: title)
	}
}
