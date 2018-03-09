//
//  ShowSentimentRouter.swift
//  Project: twitments
//
//  Module: ShowSentiment
//
//  By Ezequiel 08/03/18
//  Ezequiel 2018
//

// MARK: Imports

import UIKit
import SwiftMessages
import SwiftyVIPER

// MARK: Protocols

protocol ShowSentimentPresenterRouterProtocol: PresenterRouterProtocol {
    func presentLoader()
    func hideLoader()
}

// MARK: -

/// The Router for the ShowSentiment module
final class ShowSentimentRouter: RouterProtocol, ShowSentimentPresenterRouterProtocol {

	// MARK: - Variables
	weak var viewController: UIViewController?
    
    func presentLoader() {
        AlertMessageView.shared.loading(bottom: true)
    }
    
    func hideLoader() {
        AlertMessageView.shared.stop()
    }
}
