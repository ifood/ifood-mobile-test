//
//  MainCoordinator.swift
//  TwitterSentiment
//
//  Created by Jean Vinge on 25/12/18.
//  Copyright © 2018 Jean Vinge. All rights reserved.
//

import NetworkPlatform
import Utility

enum MainRoute {
    case home(window: UIWindow)
}

extension MainRoute: Router {
    func present() -> Transition {
        switch self {
        case .home(let window):
            var vc = FindUserViewController()
            vc.bind(to: FindUserViewModel(useCase: TwitterUseCase(), coordinator: Coordinator(vc)))
            return .rootController(window: window, viewController: vc)
        }
    }
}

class MainCoordinator: Coordinator {

    // MARK: Init

    init(_ window: UIWindow) {
        super.init(UIViewController())
        self.transition(with: MainRoute.home(window: window))
    }
}
