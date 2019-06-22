//
//  TweetListRouter.swift
//  TwitterAnalyzer
//
//  Created by Bruno Vieira on 18/06/19.
//  Copyright © 2019 Bruno Vieira. All rights reserved.
//

import UIKit

@objc protocol TweetListRoutingLogic {
    func routeToTweetSentiment()
}

protocol TweetListDataPassing {
    var dataStore: TweetListDataStore? { get }
}

class TweetListRouter: NSObject, TweetListRoutingLogic, TweetListDataPassing {
    weak var viewController: TweetListViewController?
    var dataStore: TweetListDataStore?
    
    // MARK: Routing
    
    func routeToTweetSentiment() {
        let tweetSentimentViewController = TweetSentimentViewController()
        if let viewController = viewController, let dataStore = dataStore, var tweetSentimentDataStore = tweetSentimentViewController.router?.dataStore {
            passDataToTweetSentiment(source: dataStore, destination: &tweetSentimentDataStore)
            navigateToTweetSentiment(source: viewController, destination: tweetSentimentViewController)
        }
    }
    
    // MARK: Passing data
    
    private func passDataToTweetSentiment(source: TweetListDataStore, destination: inout TweetSentimentDataStore) {
        destination.sentiment = source.selectedTweet?.sentiment
    }
    
    // MARK: Navigation
    
    private func navigateToTweetSentiment(source: TweetListViewController, destination: TweetSentimentViewController) {
        destination.modalPresentationStyle = .overCurrentContext
        destination.modalTransitionStyle = .crossDissolve
        DispatchQueue.main.async { [weak self] in
            self?.viewController?.present(destination, animated: true, completion: nil)
        }
    }
}
