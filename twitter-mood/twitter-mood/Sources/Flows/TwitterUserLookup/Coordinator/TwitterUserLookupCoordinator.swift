//
//  TwitterUserLookupCoordinator.swift
//  twitter-mood
//
//  Created by Dennis Merli on 7/7/19.
//  Copyright © 2019 Dennis Merli. All rights reserved.
//

import UIKit

class TwitterUserLookupCoordinator: BaseCoordinator {
    
    private var window: UIWindow!
    private var userLookupViewController: TwitterUserLookupViewController!
    private var navigationController: UINavigationController?
    private var userLookupViewModel: TwitterUserLookupViewModel?
    
    override init(window: UIWindow?, parentCoordinator: BaseCoordinator?) {
        super.init(window: window, parentCoordinator: parentCoordinator)
        guard let window = window else {
            fatalError("No UIWindow!")
        }
        self.window = window
    }
    
    override func start() {
        let tokenService = AccessTokenService(keychainService: KeychainService(service: ProjectUtils.keychainGroupName()))
        userLookupViewModel = TwitterUserLookupViewModel(twitterService: TwitterService(), tokenService: tokenService)
        userLookupViewController = TwitterUserLookupViewController(viewModel: userLookupViewModel as Any, nibName: TwitterUserLookupViewController.typeName)
        navigationController = CustomNavigationController(rootViewController: userLookupViewController)
        window.rootViewController = navigationController
        window.makeKeyAndVisible()
        
        userLookupViewController.seeTweetsAction.subscribe(onNext: { (_) in
            self.handleSeeUserTweets(username: try? self.userLookupViewModel?.userNameInput.value())
        }).disposed(by: disposeBag)
        
    }
    
    private func handleSeeUserTweets(username: String?) {
        guard let username = username else { return }
        let userTweetsCoordinator = UserTweetsCoordinator(username: username, rootViewControler: navigationController, parentCoordinator: self)
        self.addDependency(coordinator: userTweetsCoordinator)
        userTweetsCoordinator.start(presentationType: .push)
    }
}
