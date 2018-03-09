//
//  ShowSentimentModule.swift
//  Project: twitments
//
//  Module: ShowSentiment
//
//  By Ezequiel 08/03/18
//  Ezequiel 2018
//

// MARK: Imports

import SwiftyVIPER
import UIKit

// MARK: -

/// Used to initialize the ShowSentiment VIPER module
final class ShowSentimentModule: ModuleProtocol {

	// MARK: - Constants

	let storyboard: UIStoryboard = UIStoryboard(name: "Main", bundle: Bundle.main)

	// MARK: Variables
    private var viewModel: TwitterResultViewModel
    
	private(set) lazy var interactor: ShowSentimentInteractor = {
		ShowSentimentInteractor()
	}()

	private(set) lazy var router: ShowSentimentRouter = {
		ShowSentimentRouter()
	}()

	private(set) lazy var presenter: ShowSentimentPresenter = {
		ShowSentimentPresenter(router: self.router, interactor: self.interactor)
	}()

	private(set) lazy var view: ShowSentimentViewController = {
		var vc = self.storyboard.viewController(ShowSentimentViewController.self)
		vc.presenter = self.presenter
        vc.setViewModel(self.viewModel)
		return vc
	}()

	// MARK: - Module Protocol Variables

	var viewController: UIViewController {
		return view
	}

	// MARK: Inits

    init(viewModel:TwitterResultViewModel) {
        self.viewModel = viewModel
		presenter.view = view
		router.viewController = view
		interactor.presenter = presenter
	}
}
