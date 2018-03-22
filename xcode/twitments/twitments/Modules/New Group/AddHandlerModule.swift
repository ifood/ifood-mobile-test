//
//  AddHandlerModule.swift
//  Project: twitments
//
//  Module: AddHandler
//
//  By Ezequiel 07/03/18
//  Ezequiel 2018
//

// MARK: Imports

import SwiftyVIPER
import UIKit

// MARK: -

/// Used to initialize the AddHandler VIPER module
final class AddHandlerModule: ModuleProtocol {

	// MARK: - Constants

	let storyboard: UIStoryboard = UIStoryboard(name: "Main", bundle: Bundle.main)

	// MARK: Variables

	private(set) lazy var interactor: AddHandlerInteractor = {
		AddHandlerInteractor()
	}()

	private(set) lazy var router: AddHandlerRouter = {
		AddHandlerRouter()
	}()

	private(set) lazy var presenter: AddHandlerPresenter = {
		AddHandlerPresenter(router: self.router, interactor: self.interactor)
	}()

	private(set) lazy var view: AddHandlerViewController = {
		var vc = self.storyboard.viewController(AddHandlerViewController.self)
		vc.presenter = self.presenter
		return vc
	}()

	// MARK: - Module Protocol Variables

	var viewController: UIViewController {
		return view
	}

	// MARK: Inits

	init() {
		presenter.view = view
		router.viewController = view
		interactor.presenter = presenter
	}
}
