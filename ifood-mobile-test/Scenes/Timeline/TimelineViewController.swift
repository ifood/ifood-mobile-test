//
//  TimelineViewController.swift
//  ifood-mobile-test
//
//  Created by Marcio Garcia on 01/12/18.
//  Copyright (c) 2018 oxltech.com. All rights reserved.
//
//  This file was generated by the Clean Swift Xcode Templates so
//  you can apply clean architecture to your iOS and Mac projects,
//  see http://clean-swift.com
//

import UIKit
import TwitterKit

protocol TimelineDisplayLogic: class {
    func displayTwitterUserTimeline(viewModel: Timeline.FetchTimeline.ViewModel)
    func displayError(viewModel: Timeline.Error.ViewModel)
}

class TimelineViewController: TWTRTimelineViewController, TimelineDisplayLogic {
    
    var interactor: TimelineBusinessLogic?
    var router: (NSObjectProtocol & TimelineRoutingLogic & TimelineDataPassing)?

    // MARK: Object lifecycle

    override init(nibName nibNameOrNil: String?, bundle nibBundleOrNil: Bundle?) {
        super.init(nibName: nibNameOrNil, bundle: nibBundleOrNil)
        setup()
    }

    required init?(coder aDecoder: NSCoder) {
        super.init(coder: aDecoder)
        setup()
    }

    // MARK: Setup

    private func setup() {
        let viewController = self
        let interactor = TimelineInteractor()
        let presenter = TimelinePresenter()
        let router = TimelineRouter()
        viewController.interactor = interactor
        viewController.router = router
        interactor.presenter = presenter
        presenter.viewController = viewController
        router.viewController = viewController
        router.dataStore = interactor
    }

    // MARK: Routing

    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        if let scene = segue.identifier {
            let selector = NSSelectorFromString("routeTo\(scene)WithSegue:")
            if let router = router, router.responds(to: selector) {
                router.perform(selector, with: segue)
            }
        }
    }

    // MARK: View lifecycle

    override func viewDidLoad() {
        super.viewDidLoad()
        self.tweetViewDelegate = self
        fetchTimeline()
    }

    // MARK: Fetch Data
    
    func fetchTimeline() {
        let request = Timeline.FetchTimeline.Request(screenName: "o_antagonista")
        interactor?.fetchTwitterUserTimeline(request: request)
    }

    // MARK: Display
    
    func displayTwitterUserTimeline(viewModel: Timeline.FetchTimeline.ViewModel) {
        self.dataSource = viewModel.twitterResponse.userTimelineDataSource
    }
    
    func displayError(viewModel: Timeline.Error.ViewModel) {
        
    }
}

extension TimelineViewController: TWTRTweetViewDelegate {

    func tweetView(tweetView: TWTRTweetView, didSelectTweet tweet: TWTRTweet) {
    }
    
    func tweetView(_ tweetView: TWTRTweetView, didTap tweet: TWTRTweet) {
    }
    
}
