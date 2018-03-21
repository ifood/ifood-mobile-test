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
    func loader(show:Bool)
    func avaliateSentiment(_ message: String)
}

/// Should be conformed to by the `ShowSentimentPresenter` and referenced by `ShowSentimentInteractor`
protocol ShowSentimentInteractorPresenterProtocol: class {
    
}

// MARK: -

/// The Presenter for the ShowSentiment module
final class ShowSentimentPresenter: ShowSentimentViewPresenterProtocol, ShowSentimentInteractorPresenterProtocol, ShowSentimentDataProvider {

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
        interactor.setDataProvider(self)
	}
    
    func closeSelected() {
        router.hideLoader()
        router.dismiss(completion: nil)
    }
    
    func loader(show:Bool) {
        if show {
            router.presentLoader()
        } else {
            router.hideLoader()
        }
    }
    
    func avaliateSentiment(_ message: String) {
        interactor.avaliateSentiment(message)
    }
    
    func set(title: String) {
        view?.set(title: title)
    }
    
    // MARK: ShowSentimentDataProvider
    
    func reloadData(_ provider: AbstractDataProvider?, viewModels: TwitterSentimentViewModel) {
        print(viewModels)
    }
    
    func reloadData(_ provider: AbstractDataProvider?, sentiments: Feeling) {
        router.hideLoader()
        view?.set(sentiment: sentiments)
    }
    
    func errorData(_ provider: AbstractDataProvider?, error: NSError) {
        print(error)
        router.hideLoader()
    }
}
