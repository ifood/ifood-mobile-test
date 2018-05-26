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

    init(navigationController: UINavigationController) {
        self.navigationController = navigationController
    }

    func start() {
        let vm = TweetsListViewModel()
        let vc = TweetsTableViewController(viewModel: vm)
        navigationController.pushViewController(vc, animated: false)
    }

}

