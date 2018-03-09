//
//  ListTweetsRouter.swift
//  Project: twitments
//
//  Module: ListTweets
//
//  By Ezequiel 08/03/18
//  Ezequiel 2018
//

// MARK: Imports

import UIKit
import DeckTransition
import SwiftyVIPER

// MARK: Protocols

protocol ListTweetsPresenterRouterProtocol: PresenterRouterProtocol {
    func present(_ to: TwitterResultViewModel)
    func presentLoader()
    func hideLoader()
}

// MARK: -

final class ListTweetsRouter: RouterProtocol, ListTweetsPresenterRouterProtocol {

	// MARK: - Variables

	weak var viewController: UIViewController?
    
    func present(_ to: TwitterResultViewModel) {
        let module = ShowSentimentModule(viewModel: to)
        let transitionDelegate = DeckTransitioningDelegate()
        module.viewController.transitioningDelegate = transitionDelegate
        module.viewController.modalPresentationStyle = .custom
        module.present(from: viewController, style: .coverVertical)
    }
    
    func presentLoader() {
        AlertMessageView.shared.loading()
    }
    
    func hideLoader() {
        AlertMessageView.shared.stop()
    }
}
