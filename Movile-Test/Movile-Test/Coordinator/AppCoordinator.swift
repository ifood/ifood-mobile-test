//
//  AppCoordinator.swift
//  Movile-Test
//
//  Created by Thiago Felipe Alves on 14/07/19.
//  Copyright © 2019 Thalves. All rights reserved.
//

import UIKit

class AppCoordinator: NSObject {

    var window: UIWindow

    var homeCoordinator: HomeCoordinator?

    required init(window: UIWindow) {
        self.window = window
        self.window.makeKeyAndVisible()
    }

    func start() {
        homeCoordinator = HomeCoordinator(window: self.window)
        homeCoordinator?.start()
    }
}
