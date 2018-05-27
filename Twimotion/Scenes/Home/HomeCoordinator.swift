//
//  HomeCoordinator.swift
//  Twimotion
//
//  Created by Antony on 27/05/18.
//  Copyright © 2018 Twimotion. All rights reserved.
//

import Foundation
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

    func didLoadUser(user: TwitterUser) {
        let tweetsCoordinator = TweetsCoordinator(
            twitterUser: user,
            navigationController: navigationController
        )
        tweetsCoordinator.start()
        childCoordinators.append(tweetsCoordinator)
    }

}
