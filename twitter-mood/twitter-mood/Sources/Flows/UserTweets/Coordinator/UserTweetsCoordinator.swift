//
//  UserTweetsCoordinator.swift
//  twitter-mood
//
//  Created by Dennis Merli on 7/7/19.
//  Copyright © 2019 Dennis Merli. All rights reserved.
//

import UIKit

class UserTweetsCoordinator: BaseCoordinator {
    private var userTweetsViewController: UserTweetsViewController?
    private var userTweetsViewModel: UserTweetsViewModel?
    private var router: Router!
    private var userName: String
    
    init(username: String, rootViewControler: UIViewController?, parentCoordinator: BaseCoordinator?) {
        self.userName = username
        super.init(rootViewControler: rootViewControler, parentCoordinator: parentCoordinator)
        self.router = Router(rootController: rootViewControler)
    }
    
    override func start(presentationType: PresentationType) {
        userTweetsViewModel = UserTweetsViewModel(username: userName, twitterService: TwitterService())
        userTweetsViewController = UserTweetsViewController(viewModel: userTweetsViewModel as Any, nibName: UserTweetsViewController.typeName)
        userTweetsViewController?.navigationControllerPopped.subscribe(onNext: { [weak self] (_) in
            self?.finalize()
        }).disposed(by: disposeBag)
        router.showWithPresentationType(viewController: userTweetsViewController, presentationType: presentationType)
    }
    
    override func finalize() {
        parentCoordinator?.removeDependency(coordinator: self)
    }
}
