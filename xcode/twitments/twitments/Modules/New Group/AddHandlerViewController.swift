//
//  AddHandlerViewController.swift
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

/// Should be conformed to by the `AddHandlerViewController` and referenced by `AddHandlerPresenter`
protocol AddHandlerPresenterViewProtocol: class {
    func authenticationStatus(_ status: Bool)
}

// MARK: -

/// The View Controller for the AddHandler module
class AddHandlerViewController: AbstractViewController, StoryboardIdentifiable, AddHandlerPresenterViewProtocol {

	// MARK: - Constants

	// MARK: Variables

	var presenter: AddHandlerViewPresenterProtocol?
    
    @IBOutlet weak private(set) var messageLabel: UILabel? {
        willSet(label) {
            label?.textAlignment = .center
        }
    }
    
    @IBOutlet weak private(set) var handleTextField: UITextField? {
        willSet(textfield) {
            textfield?.textAlignment = .center
        }
    }
    
    @IBOutlet weak private(set) var continueButton: UIButton? {
        willSet(button) {
            button?.addTarget(self, action: #selector(continueTapped), for: .touchUpInside)
        }
    }
    
    // MARK: - Functions
    
    @objc
    func continueTapped() {
        guard let text = self.handleTextField?.text else {
            return
        }
        if !text.isEmpty {
            AlertMessageView().loading()
            presenter?.continueTapped(text)
        }
    }

	// MARK: - Load Functions

	override func viewDidLoad() {
    	super.viewDidLoad()
		presenter?.viewLoaded()
        view.backgroundColor = .white
        AlertMessageView().loading()
        presenter?.authentication()
    }

	// MARK: - AddHandler Presenter to View Protocol

	func authenticationStatus(_ status: Bool) {
		self.continueButton?.isEnabled = status
        AlertMessageView().showFinished()
	}
}
