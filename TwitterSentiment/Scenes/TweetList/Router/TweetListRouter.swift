//
//  TweetListRouter.swift
//  TwitterSentiment
//
//  Created by Jean Vinge on 27/12/18.
//  Copyright © 2018 Jean Vinge. All rights reserved.
//

import Domain
import Network
import Utility
import Resources
import RxSwift
import RxCocoa

enum TweetListRouter {
    case analizeSentiment(tweet: Tweet)
    case error(error: Error)
}

extension TweetListRouter: Router {
    func present() -> Transition {
        switch self {
        case .analizeSentiment(let tweet):
            var vc = SentimentViewController.init(theme: ClearTheme())
            vc.bind(to: SentimentViewModel(tweet: tweet, useCase: TextAnalizerUseCase(), coordinator: Coordinator(vc)))
            vc.modalPresentationStyle = .overCurrentContext
            return .present(viewController: vc, animated: true)
        case .error(let error):
            return .present(viewController: UIAlertController(alerWithError: error), animated: true)
        }
    }
}
