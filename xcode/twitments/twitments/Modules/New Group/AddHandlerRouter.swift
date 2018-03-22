//
//  AddHandlerRouter.swift
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
import DeckTransition

// MARK: Protocols

protocol AddHandlerPresenterRouterProtocol: PresenterRouterProtocol {
    func present(_ list: [TwitterResultViewModel])
    func presentLoader()
    func hideLoader()
}

// MARK: -

/// The Router for the AddHandler module
final class AddHandlerRouter: RouterProtocol, AddHandlerPresenterRouterProtocol {

    // MARK: - Variables

    weak var viewController: UIViewController?

    func present(_ list: [TwitterResultViewModel]) {
        let module = ListTweetsModule(viewModels: list)
        let transitionDelegate = DeckTransitioningDelegate()
        module.viewController.transitioningDelegate = transitionDelegate
        module.viewController.modalPresentationStyle = .custom
        module.present(from: viewController, style: .coverVertical)
    }

    func presentLoader() {
        AlertMessageView.shared.loading(bottom: true)
    }

    func hideLoader() {
        AlertMessageView.shared.stop()
    }
}
