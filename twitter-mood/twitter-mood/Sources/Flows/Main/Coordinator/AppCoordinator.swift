//
//  AppCoordinator.swift
//  twitter-mood
//
//  Created by Dennis Merli on 7/7/19.
//  Copyright © 2019 Dennis Merli. All rights reserved.
//

import UIKit
import RxSwift

final class AppCoordinator: BaseCoordinator {
    private var window: UIWindow?
    
    override init(window: UIWindow?, parentCoordinator: BaseCoordinator?) {
        super.init(window: window, parentCoordinator: parentCoordinator)
        self.window = window
    }
    
    override func start() {
        startHomeFlow()
    }
    
    private func startHomeFlow() {
        let userLookupCoordinator = TwitterUserLookupCoordinator(window: self.window, parentCoordinator: self)
        self.coordinateTo(coordinator: userLookupCoordinator)
    }
}

