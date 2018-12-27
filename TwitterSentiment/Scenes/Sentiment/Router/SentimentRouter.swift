//
//  SentimentRouter.swift
//  TwitterSentiment
//
//  Created by Jean Vinge on 27/12/18.
//  Copyright © 2018 Jean Vinge. All rights reserved.
//

import Utility

enum SentimentRouter {
    case dismiss
}

extension SentimentRouter: Router {
    func present() -> Transition {
        switch self {
        case .dismiss:
            return .dismiss(animated: true)
        }
    }
}
