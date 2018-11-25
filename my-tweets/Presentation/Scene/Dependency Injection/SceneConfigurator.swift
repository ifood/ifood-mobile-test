//
//  SceneConfigurator.swift
//  my-tweets
//
//  Created by Gabriel Catice on 25/11/18.
//  Copyright © 2018 Gabriel Catice. All rights reserved.
//

import Swinject
import SwinjectAutoregistration
import RxSwift
import RxCocoa

class HomeViewControllerConfigurator {
    private static var container = Container()
    static func setup(with container: Container, coordinator: Coordinator) {
        self.container = container
        
        HomePresenterConfigurator.setup(with: container)
        container.register(HomeViewProtocol.self) { _ in
            HomeViewController.instantiate()
            }.initCompleted { (resolver, vc) in
                guard let viewController = vc as? HomeViewController  else { return }
                viewController.presenter = resolver.resolve(HomePresenterProtocol.self)
        }
    }
    
    class func viewController() -> HomeViewController? {
        guard let viewController = HomeViewControllerConfigurator.containerViewController  else { return nil }
        return viewController
    }
    
    fileprivate class var containerViewController: HomeViewController? {
        return container.resolve(HomeViewProtocol.self) as? HomeViewController
    }
}
