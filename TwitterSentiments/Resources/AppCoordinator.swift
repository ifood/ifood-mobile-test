//
//  AppCoordinator.swift
//  TwitterSentiments
//
//  Created by Marina Azevedo on 14/07/19.
//  Copyright © 2019 Marina Azevedo. All rights reserved.
//

import Foundation
import UIKit

class AppCoordinator: Coordinator {
    var childCoordinators: [Coordinator] = [Coordinator]()
    var rootViewController: UIViewController { return navigationController }
    
    private var navigationController: UINavigationController
    
    init() {
        self.navigationController = UINavigationController()
    }
    
    func start() {
        let homeCoordinator = HomeCoordinator(navigationController: navigationController)
        homeCoordinator.start()
        childCoordinators.append(homeCoordinator)
    }
    
}
