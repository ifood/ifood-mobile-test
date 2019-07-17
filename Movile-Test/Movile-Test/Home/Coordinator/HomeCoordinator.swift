//
//  HomeCoordinator.swift
//  Movile-Test
//
//  Created by Thiago Felipe Alves on 14/07/19.
//  Copyright © 2019 Thalves. All rights reserved.
//

import UIKit

protocol HomeCoordinatorDelegate: AnyObject {
    func openDetail(tweet: Tweet)
}

class HomeCoordinator: NSObject {

    // MARK: - Properties
    var window: UIWindow

    var viewController: HomeVC?
    var navigation: UINavigationController?
    var detailCoordinator: DetailCoordinator?

    // MARK: - Initializers
    required init(window: UIWindow) {
        self.window = window
    }

    func start() {

        let viewModel = HomeVM()
        viewModel.delegate = self
        viewController = HomeVC(viewModel: viewModel)

        navigation = UINavigationController(rootViewController: viewController!)
        navigation?.navigationBar.barTintColor = UIColor(hexString: "#1dcaff")
        navigation?.navigationBar.tintColor = .white
        navigation?.navigationBar.titleTextAttributes = [NSAttributedString.Key.foregroundColor: UIColor.white]
        navigation?.navigationBar.isOpaque = false

        window.rootViewController = navigation
    }
}

extension HomeCoordinator: HomeCoordinatorDelegate {

    func openDetail(tweet: Tweet) {
        let coordinator = DetailCoordinator(tweet: tweet)
        self.navigation?.pushViewController(coordinator.start(), animated: true)

        self.detailCoordinator = coordinator
    }
}
