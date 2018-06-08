//
//  TwitterUserRouter.swift
//  iFoodTest
//
//  Created by Mauricio Cesar Maniglia Junior on 06/06/18.
//  Copyright © 2018 Mauricio Cesar Maniglia Junior. All rights reserved.
//

import UIKit

final class TwitterUserRouter: TwitterUserRouterProtocol {
    weak var viewController: UIViewController?
    
    func presentTweetListFor(user twitterUser: String) {
        let tweetListModuleViewController = TweetListBuilder.assembleModule(forTwitterUser: twitterUser)
        viewController?.navigationController?.pushViewController(tweetListModuleViewController, animated: true)
    }
}
