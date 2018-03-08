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
	/** Sets the title for the view
	- parameters:
		- title The title to set
	*/
	func set(title: String?)
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
            textfield?.textAlignment = .left
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
        presenter?.continueTapped()
    }

	// MARK: - Load Functions

	override func viewDidLoad() {
    	super.viewDidLoad()
		presenter?.viewLoaded()
        view.backgroundColor = .white
    }

	// MARK: - AddHandler Presenter to View Protocol

	func set(title: String?) {
		self.title = title
	}
}
