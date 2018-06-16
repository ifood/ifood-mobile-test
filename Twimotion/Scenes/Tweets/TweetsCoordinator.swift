//
//  TweetsCoordinator.swift
//  Twimotion
//
//  Created by Antony on 26/05/18.
//  Copyright © 2018 Twimotion. All rights reserved.
//

import Foundation
import UIKit

class TweetsCoordinator: Coordinator {

    var childCoordinators: [Coordinator] = [Coordinator]()
    var rootViewController: UIViewController { return navigationController }

    var navigationController: UINavigationController
    var tweetSentimentViewController: TweetSentimentViewController?

    var twitterUser: TwitterUser

    // MARK: - Data Sources

    private let naturalLangDataSource = NaturalLangDataSource()
    private let twitterDataSource = TwitterDataSource()

    // MARK: - Initializers

    init(twitterUser: TwitterUser, navigationController: UINavigationController) {
        self.twitterUser = twitterUser
        self.navigationController = navigationController
    }

    func start() {
        let vm = TweetsListViewModel(twitterUser: twitterUser, twitterDataSource: twitterDataSource)
        vm.delegate = self

        let vc = TweetListViewController(viewModel: vm)
        navigationController.pushViewController(vc, animated: true)
    }

}

// MARK: - TweetsListViewModelDelegate
extension TweetsCoordinator: TweetsListViewModelDelegate {

    func didSelectTweet(_ tweet: Tweet) {

        let vm = TweetSentimentViewModel(tweet: tweet, naturalLangDataSource: naturalLangDataSource)
        vm.delegate = self

        tweetSentimentViewController = TweetSentimentViewController(viewModel: vm)
        navigationController
            .topViewController?
            .present(tweetSentimentViewController!, animated: true, completion: nil)
    }

}

// MARK: - TweetSentimentViewModelDelegate
extension TweetsCoordinator: TweetSentimentViewModelDelegate {

    func didDismissedPopup() {
        tweetSentimentViewController?.dismiss(animated: true, completion: nil)
    }

}
