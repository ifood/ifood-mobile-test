//
//  HomeConfigurator.swift
//  my-tweets
//
//  Created by Gabriel Catice on 25/11/18.
//  Copyright © 2018 Gabriel Catice. All rights reserved.
//

import Swinject
import SwinjectAutoregistration
import UIKit

class HomeConfigurator {
    private static var container = Container()
    static func setup(with container: Container) {
        self.container = container
        container.register(HomeCoordinator.self) { (_, navigationController) -> HomeCoordinator in
            HomeCoordinator(navigationController: navigationController)
            }.initCompleted { (_, coordinator) in
                //setup all scenes on flow
                HomeViewControllerConfigurator.setup(with: container, coordinator: coordinator)
        }
    }
    
    class func coordinator(with navigationController: UINavigationController) -> HomeCoordinator? {
        guard let coordinator = container.resolve(HomeCoordinator.self, argument: navigationController)
            else { return nil }
        return coordinator
    }
}

