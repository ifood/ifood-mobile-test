//
//  ShowSentimentViewController.swift
//  Project: twitments
//
//  Module: ShowSentiment
//
//  By Ezequiel 08/03/18
//  Ezequiel 2018
//

// MARK: Imports

import UIKit

import SwiftyVIPER

// MARK: Protocols

/// Should be conformed to by the `ShowSentimentViewController` and referenced by `ShowSentimentPresenter`
protocol ShowSentimentPresenterViewProtocol: class {
	/** Sets the title for the view
	- parameters:
		- title The title to set
	*/
	func set(title: String?)
}

// MARK: -

/// The View Controller for the ShowSentiment module
class ShowSentimentViewController: UIViewController, StoryboardIdentifiable, ShowSentimentPresenterViewProtocol {

	// MARK: - Constants

	// MARK: Variables

	var presenter: ShowSentimentViewPresenterProtocol?
    private var viewModel: TwitterResultViewModel?
    
    @IBOutlet weak private(set) var closeButton: UIButton? {
        willSet(button) {
            button?.addTarget(self, action: #selector(closeSelected), for: .touchUpInside)
        }
    }
    
    @IBOutlet weak private(set) var borderVIew: UIView? {
        willSet(view) {
            view?.clipsToBounds = true
            view?.layer.cornerRadius = 20
        }
    }
    
    @IBOutlet weak private(set) var tweetLabel: UILabel? {
        willSet(tweetLabel) {
            tweetLabel?.text = self.viewModel?.text
        }
    }

	// MARK: - Load Functions

	override func viewDidLoad() {
    	super.viewDidLoad()
		presenter?.viewLoaded()
        presenter?.presentLoader()
    }
    
    @objc
    func closeSelected() {
        presenter?.closeSelected()
    }
    
    func setViewModel(_ viewModel:TwitterResultViewModel) {
        self.viewModel = viewModel
    }
    

	// MARK: - ShowSentiment Presenter to View Protocol

	func set(title: String?) {
		self.title = title
	}
}
