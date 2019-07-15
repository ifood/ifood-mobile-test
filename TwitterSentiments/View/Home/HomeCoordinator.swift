//
//  HomeCoordinator.swift
//  TwitterSentiments
//
//  Created by Marina Azevedo on 14/07/19.
//  Copyright © 2019 Marina Azevedo. All rights reserved.
//

import UIKit

class HomeCoordinator: Coordinator {
    
    var childCoordinators: [Coordinator] = [Coordinator]()
    var rootViewController: UIViewController { return navigationController }
    
    var navigationController: UINavigationController
    
    init(navigationController: UINavigationController) {
        self.navigationController = navigationController
    }
    
    func start() {
        let twitterDataSource = TwitterDataSource()
        let vm = HomeViewModel(twitterDataSource: twitterDataSource)
        vm.delegate = self
        let vc = HomeViewController(viewModel: vm)
        
        navigationController.pushViewController(vc, animated: false)
    }
    
}

extension HomeCoordinator: HomeViewModelDelegate {
    
    func didLoadUser(user: User) {
        let tweetsCoordinator = TweetsCoordinator (
            twitterUser: user,
            navigationController: navigationController
        )
        tweetsCoordinator.start()
        childCoordinators.append(tweetsCoordinator)
    }
    
}
