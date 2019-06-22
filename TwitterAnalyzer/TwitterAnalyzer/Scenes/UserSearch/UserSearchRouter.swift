//
//  UserSearchRouter.swift
//  TwitterAnalyzer
//
//  Created by Bruno Vieira on 18/06/19.
//  Copyright © 2019 Bruno Vieira. All rights reserved.
//

import UIKit

@objc protocol UserSearchRoutingLogic {
    func routeToTweetList()
}

protocol UserSearchDataPassing {
    var dataStore: UserSearchDataStore? { get }
}

class UserSearchRouter: NSObject, UserSearchRoutingLogic, UserSearchDataPassing {
    weak var viewController: UserSearchViewController?
    var dataStore: UserSearchDataStore?
    
    // MARK: Routing
    
    func routeToTweetList() {
        let tweetListViewController = TweetListViewController()
        if let viewController = viewController, let dataStore = dataStore, var tweetListDataStore = tweetListViewController.router?.dataStore {
            passDataToTweetList(source: dataStore, destination: &tweetListDataStore)
            navigateToTweetList(source: viewController, destination: tweetListViewController)
        }
    }
    
    // MARK: Passing data
    
    private func passDataToTweetList(source: UserSearchDataStore, destination: inout TweetListDataStore) {
        destination.user = source.user
    }
    
    // MARK: Navigation
    
    private func navigateToTweetList(source: UserSearchViewController, destination: TweetListViewController) {
        viewController?.navigationController?.pushViewController(destination, animated: true)
    }
}
