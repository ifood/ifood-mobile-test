//
//  ShowSentimentInteractor.swift
//  Project: twitments
//
//  Module: ShowSentiment
//
//  By Ezequiel 08/03/18
//  Ezequiel 2018
//

// MARK: Imports

import Foundation
import SwiftyVIPER

// MARK: Protocols

/// Should be conformed to by the `ShowSentimentInteractor` and referenced by `ShowSentimentPresenter`
protocol ShowSentimentPresenterInteractorProtocol {
	func avaliateSentiment(_ message: String)
    func setDataProvider(_ dataProvider: ShowSentimentDataProvider)
}

// MARK: -

/// The Interactor for the ShowSentiment module
final class ShowSentimentInteractor: ShowSentimentPresenterInteractorProtocol {
    
	// MARK: - Variables

	weak var presenter: ShowSentimentInteractorPresenterProtocol?
    fileprivate var manager: ShowSentimentDataProviderManager?
	// MARK: - ShowSentiment Presenter to Interactor Protocol
    
    func setDataProvider(_ dataProvider: ShowSentimentDataProvider) {
        manager = ShowSentimentDataProviderManager()
        manager?.dataProvider = dataProvider
    }
    
    func avaliateSentiment(_ message: String) {
        manager?.avaliateSentiment(message)
    }
}
