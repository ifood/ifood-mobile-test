//
//  Router.swift
//  twitter-mood
//
//  Created by Dennis Merli on 7/6/19.
//  Copyright © 2019 Dennis Merli. All rights reserved.
//

import UIKit

enum PresentationType {
    case present
    case push
}

protocol RouterType {
    func popToRoot(animated: Bool)
    func present(viewController: UIViewController?, animated: Bool)
    func dismiss(animated: Bool, completion: (() -> Void)?)
    func dismiss(viewController: UIViewController?, animated: Bool, completion: (() -> Void)?)
    func push(viewController: UIViewController?, animated: Bool, completion: (() -> Void)?)
    func pop(animated: Bool)
    func showWithPresentationType(viewController: UIViewController?, presentationType: PresentationType)
}

final class Router: RouterType {
    
    private(set) weak var rootController: UIViewController?
    /// Only have to be used in case we have a present of the rootController and then a push
    private(set) weak var navigationController: UINavigationController?
    
    init(rootController: UIViewController?) {
        guard let unwrappedRootController = rootController else { return }
        self.rootController = unwrappedRootController
    }
    
    /// Only have to be used in case we have a present of the rootController and then a push
    func setNavigationController(navigationController: UINavigationController?) {
        self.navigationController = navigationController
    }
    
    func popToRoot(animated: Bool) {
        guard let navController = getNavigationController() else { return }
        navController.popToRootViewController(animated: animated)
    }
    
    func present(viewController: UIViewController?, animated: Bool) {
        DispatchQueue.main.async {
            guard let controller = viewController else { return }
            self.rootController?.present(controller, animated: animated, completion: nil)
        }
    }
    
    func dismiss(animated: Bool, completion: (() -> Void)?) {
        rootController?.dismiss(animated: animated, completion: completion)
    }
    
    func dismiss(viewController: UIViewController?, animated: Bool, completion: (() -> Void)?) {
        viewController?.dismiss(animated: animated, completion: completion)
    }
    
    func push(viewController: UIViewController?, animated: Bool, completion: (() -> Void)?) {
        DispatchQueue.main.async {
            guard let navController = self.getNavigationController() else {
                print("Did not find any Navigation Controller")
                return
            }
            
            guard let viewController = viewController else { return }
            navController.pushViewController(viewController, animated: animated)
        }
    }
    
    func pop(animated: Bool) {
        guard let navController = getNavigationController() else { return }
        navController.popViewController(animated: true)
    }
    
    func showWithPresentationType(viewController: UIViewController?, presentationType: PresentationType) {
        switch presentationType {
        case .present:
            present(viewController: viewController, animated: true)
        case .push:
            push(viewController: viewController, animated: true, completion: nil)
        }
    }
    
    private func getNavigationController() -> UINavigationController? {
        var navController: UINavigationController?
        if let rootNavigationViewController = rootController as? UINavigationController {
            navController = rootNavigationViewController
        } else if let routerNavController = self.navigationController {
            navController = routerNavController
        }
        return navController
    }
}

