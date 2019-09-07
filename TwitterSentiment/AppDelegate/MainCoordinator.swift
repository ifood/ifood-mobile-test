//
//  MainCoordinator.swift
//  TwitterSentiment
//
//  Created by Jean Vinge on 25/12/18.
//  Copyright © 2018 Jean Vinge. All rights reserved.
//

import NetworkPlatform
import Utility

class MainCoordinator: CoordinatorProvider {

    // MARK: Init
    
    func start(window: UIWindow) {
        var vc = FindUserViewController()
        vc.bind(to: FindUserViewModel(useCase: TwitterUseCase(), router: ScreenRouter(vc)))
        self.rootViewController(in: window, with: vc)
    }
}
