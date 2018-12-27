//
//  FindUserRouter.swift
//  TwitterSentiment
//
//  Created by Jean Vinge on 26/12/18.
//  Copyright © 2018 Jean Vinge. All rights reserved.
//

import Domain
import Utility

enum FindUserRouter {
    case toTweetList(user: TwitterUser, useCase: TwitterUseCase)
    case error(error: Error)
}

extension FindUserRouter: Router {
    func present() -> Transition {
        switch self {
        case .toTweetList(let user, let useCase):
            var vc = TweetListViewController()
            vc.bind(to: TweetListViewModel(user: user, useCase: useCase, coordinator: Coordinator(vc)))
            return .present(viewController: CloseButtonDecorator.closeButton(vc), animated: true)
        case .error(let error):
            return .present(viewController: UIAlertController(alerWithError: error), animated: true)
        }
    }
}
