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
	/// Requests the title for the presenter
	func requestTitle()
}

// MARK: -

/// The Interactor for the ShowSentiment module
final class ShowSentimentInteractor: ShowSentimentPresenterInteractorProtocol {

	// MARK: - Variables

	weak var presenter: ShowSentimentInteractorPresenterProtocol?

	// MARK: - ShowSentiment Presenter to Interactor Protocol

	func requestTitle() {
		presenter?.set(title: "ShowSentiment")
	}
}
