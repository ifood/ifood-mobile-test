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

// MARK: Protocols

/// Should be conformed to by the `AddHandlerRouter` and referenced by `AddHandlerPresenter`
protocol AddHandlerPresenterRouterProtocol: PresenterRouterProtocol {
    func present(_ list:[TwitterResultViewModel])
}

// MARK: -

/// The Router for the AddHandler module
final class AddHandlerRouter: RouterProtocol, AddHandlerPresenterRouterProtocol {
    
    // MARK: - Variables
    
    weak var viewController: UIViewController?
    
    func present(_ list: [TwitterResultViewModel]) {
        list.map {
            print($0)
            print("\n\n\nbatata")
        }
    }
    
}
