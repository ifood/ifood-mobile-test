//
//  TweetSentimentViewController.swift
//  TwitterAnalyzer
//
//  Created by Bruno Vieira on 18/06/19.
//  Copyright © 2019 Bruno Vieira. All rights reserved.
//

import UIKit

protocol TweetSentimentDisplayLogic: class {
    func displayLoadInformation(viewModel: TweetSentiment.LoadInformation.ViewModel)
}

class TweetSentimentViewController: UIViewController, TweetSentimentDisplayLogic {
    var interactor: TweetSentimentBusinessLogic?
    var router: (NSObjectProtocol & TweetSentimentRoutingLogic & TweetSentimentDataPassing)?
    private lazy var tweetSentimentView = TweetSentimentView()
    
    // MARK: Object lifecycle
    
    override init(nibName nibNameOrNil: String?, bundle nibBundleOrNil: Bundle?) {
        super.init(nibName: nibNameOrNil, bundle: nibBundleOrNil)
        setup()
    }
    
    required init?(coder aDecoder: NSCoder) {
        super.init(coder: aDecoder)
        setup()
    }
    
    override func loadView() {
        super.loadView()
        view = tweetSentimentView
    }
    
    // MARK: Setup
    
    private func setup() {
        let viewController = self
        let interactor = TweetSentimentInteractor(presenter: TweetSentimentPresenter(viewController: self), worker: TweetSentimentWorker())
        let router = TweetSentimentRouter()
        viewController.interactor = interactor
        viewController.router = router
        router.viewController = viewController
        router.dataStore = interactor
    }
    
    // MARK: View lifecycle
    
    override func viewDidLoad() {
        super.viewDidLoad()
        setupActions()
    }
    
    override func viewWillAppear(_ animated: Bool) {
        super.viewWillAppear(animated)
        loadInformation()
    }
    
    func setupActions() {
        tweetSentimentView.okButton.addTarget(self, action: #selector(okButtonDidReceiveTouchUpInside), for: .touchUpInside)
    }
    
    @objc func okButtonDidReceiveTouchUpInside() {
        dismiss(animated: true, completion: nil)
    }
    
    // MARK: Load Information
    func loadInformation() {
        interactor?.loadInformation(request: TweetSentiment.LoadInformation.Request())
    }
    
    func displayLoadInformation(viewModel: TweetSentiment.LoadInformation.ViewModel) {
        tweetSentimentView.emojiLabel.text = viewModel.emoji
        tweetSentimentView.emojiContainerView.backgroundColor = viewModel.backgroundColor
    }
}
