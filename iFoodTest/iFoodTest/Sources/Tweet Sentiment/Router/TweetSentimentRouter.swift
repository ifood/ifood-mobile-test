//
//  TweetSentimentRouter.swift
//  iFoodTest
//
//  Created by Mauricio Cesar Maniglia Junior on 09/06/18.
//  Copyright © 2018 Mauricio Cesar Maniglia Junior. All rights reserved.
//

import UIKit

final class TweetSentimentRouter: TweetSentimentRouterProtocol {
    weak var viewController: UIViewController?
    
    func closeTweetSentimentScreen() {
        viewController?.dismiss(animated: true, completion: nil)
    }
}
