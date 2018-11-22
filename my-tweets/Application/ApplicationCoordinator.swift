//
//  ApplicationCoordinator.swift
//  my-tweets
//
//  Created by Gabriel Catice on 22/11/18.
//  Copyright © 2018 Gabriel Catice. All rights reserved.
//

import UIKit
import Swinject
import RxSwift

class ApplicationCoordinator {
    var disposeBag = DisposeBag()
    var children: [Coordinator] = []
    let container: Container = buildApplicationContainer()
    let window: UIWindow
    
    init(window: UIWindow) {
        self.window = window
    }
    
    func start() {
//        let tabBarController = CustomTabBarController()
//        disposeBag = DisposeBag()
//
//        // First tab
//        let firstNavigationController = UINavigationController()
//        firstNavigationController.tabBarItem = UITabBarItem(title: R.string.localizable.refund().uppercased(),
//                                                            image: R.image.icRefundOff(), selectedImage: R.image.icRefundOn())
//        let firstCoordinator = MyRefundsConfigurator.coordinator(with: firstNavigationController)!
//        firstCoordinator.start()
//        children.append(firstCoordinator)
//
//        tabBarController.setViewControllers([firstNavigationController], animated: false)
        
//        self.window.rootViewController = tabBarController
        self.window.makeKeyAndVisible()
    }
}
