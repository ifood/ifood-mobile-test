//
//  HomeCoordinator.swift
//  Movile-Test
//
//  Created by Thiago Felipe Alves on 14/07/19.
//  Copyright © 2019 Thalves. All rights reserved.
//

import UIKit

class HomeCoordinator: NSObject {

    var window: UIWindow

    var viewController: HomeVC?
    var navigation: UINavigationController?

    required init(window: UIWindow) {
        self.window = window
    }

    func start() {

        let viewModel = HomeVM()
        viewController = HomeVC(viewModel: viewModel)

        navigation = UINavigationController(rootViewController: viewController!)
        navigation?.navigationBar.barTintColor = UIColor(hexString: "#1dcaff")
        navigation?.navigationBar.tintColor = .white
        navigation?.navigationBar.titleTextAttributes = [NSAttributedString.Key.foregroundColor: UIColor.white]
        navigation?.navigationBar.isOpaque = false

        window.rootViewController = navigation
    }
}
