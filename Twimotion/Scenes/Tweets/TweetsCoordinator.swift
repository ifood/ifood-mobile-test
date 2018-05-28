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

    var twitterUser: TwitterUser

    init(twitterUser: TwitterUser, navigationController: UINavigationController) {
        self.twitterUser = twitterUser
        self.navigationController = navigationController
    }

    func start() {
        let vm = TweetsListViewModel(
            twitterUser: twitterUser,
            twitterDataSource: TwitterDataSource()
        )
        let vc = TweetListViewController(viewModel: vm)

        navigationController.pushViewController(vc, animated: true)
    }

}

