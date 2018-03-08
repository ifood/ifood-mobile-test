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

import SwiftyVIPER

// MARK: Protocols

/// Should be conformed to by the `ListTweetsRouter` and referenced by `ListTweetsPresenter`
protocol ListTweetsPresenterRouterProtocol: PresenterRouterProtocol {

}

// MARK: -

/// The Router for the ListTweets module
final class ListTweetsRouter: RouterProtocol, ListTweetsPresenterRouterProtocol {

	// MARK: - Variables

	weak var viewController: UIViewController?
}
