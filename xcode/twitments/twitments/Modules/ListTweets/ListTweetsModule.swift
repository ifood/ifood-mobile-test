//
//  ListTweetsModule.swift
//  Project: twitments
//
//  Module: ListTweets
//
//  By Ezequiel 08/03/18
//  Ezequiel 2018
//

// MARK: Imports

import SwiftyVIPER
import UIKit

// MARK: -

/// Used to initialize the ListTweets VIPER module
final class ListTweetsModule: ModuleProtocol {

	// MARK: - Constants

	let storyboard: UIStoryboard = UIStoryboard(name: "Main", bundle: Bundle.main)

	// MARK: Variables

    private var viewModels: [TwitterResultViewModel]

	private(set) lazy var interactor: ListTweetsInteractor = {
		ListTweetsInteractor()
	}()

	private(set) lazy var router: ListTweetsRouter = {
		ListTweetsRouter()
	}()

	private(set) lazy var presenter: ListTweetsPresenter = {
		ListTweetsPresenter(router: self.router, interactor: self.interactor)
	}()

	private(set) lazy var view: ListTweetsViewController = {
		var vc = self.storyboard.viewController(ListTweetsViewController.self)
		vc.presenter = self.presenter
        vc.setViewModels(self.viewModels)
		return vc
	}()

	// MARK: - Module Protocol Variables

	var viewController: UIViewController {
		return view
	}

	// MARK: Inits

    init(viewModels: [TwitterResultViewModel]) {
        self.viewModels = viewModels
		presenter.view = view
		router.viewController = view
		interactor.presenter = presenter
	}
}
