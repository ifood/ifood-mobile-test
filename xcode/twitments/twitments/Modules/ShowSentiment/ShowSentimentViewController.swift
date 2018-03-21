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

protocol ShowSentimentPresenterViewProtocol: class {
	func set(sentiment: Feeling)
    func set(title: String)
}

// MARK: -

/// The View Controller for the ShowSentiment module
class ShowSentimentViewController: UIViewController, StoryboardIdentifiable, ShowSentimentPresenterViewProtocol {

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
    
    @IBOutlet weak var sentimentLabel: UILabel!
    
	// MARK: - Load Functions

	override func viewDidLoad() {
    	super.viewDidLoad()
		presenter?.viewLoaded()
        presenter?.loader(show: true)
        if let tweet = (viewModel?.text) {
            presenter?.avaliateSentiment(tweet)
        } else {
            presenter?.loader(show: false)
        }
    }
    
    @objc
    func closeSelected() {
        presenter?.closeSelected()
    }
    
    func setViewModel(_ viewModel:TwitterResultViewModel) {
        self.viewModel = viewModel
    }
    

	// MARK: - ShowSentiment Presenter to View Protocol
    
    func set(sentiment: Feeling) {
        self.sentimentLabel.isHidden = false
        self.sentimentLabel.text = sentiment.emojiValue
    }
    
    func set(title: String) {
        self.title = title
    }
}
