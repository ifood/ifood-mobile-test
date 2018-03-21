//
//  ListTweetsInteractor.swift
//  Project: twitments
//
//  Module: ListTweets
//
//  By Ezequiel 08/03/18
//  Ezequiel 2018
//

// MARK: Imports

import Foundation
import SwiftyVIPER

// MARK: Protocols

/// Should be conformed to by the `ListTweetsInteractor` and referenced by `ListTweetsPresenter`
protocol ListTweetsPresenterInteractorProtocol {
	/// Requests the title for the presenter
	func requestTitle()
}

// MARK: -

/// The Interactor for the ListTweets module
final class ListTweetsInteractor: ListTweetsPresenterInteractorProtocol {

	// MARK: - Variables

	weak var presenter: ListTweetsInteractorPresenterProtocol?

	// MARK: - ListTweets Presenter to Interactor Protocol

	func requestTitle() {
		presenter?.set(title: "Tweet's Feed")
	}
}
