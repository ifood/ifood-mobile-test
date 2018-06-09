//
//  TweetListRouter.swift
//  iFoodTest
//
//  Created by Mauricio Cesar Maniglia Junior on 07/06/18.
//  Copyright © 2018 Mauricio Cesar Maniglia Junior. All rights reserved.
//

import UIKit

final class TweetListRouter: TweetListRouterProtocol {
    weak var viewController: UIViewController?
    
    func presentTweetSentimentForTweet(_ tweet: Tweet) {
        let tweetSentimentModuleViewController = TweetSentimentBuilder.assembleModule(forTweet: tweet)
        tweetSentimentModuleViewController.definesPresentationContext = true
        tweetSentimentModuleViewController.providesPresentationContextTransitionStyle = true
        tweetSentimentModuleViewController.modalPresentationStyle = .overCurrentContext
        tweetSentimentModuleViewController.modalTransitionStyle = .crossDissolve
        
        viewController?.navigationController?.present(tweetSentimentModuleViewController, animated: true, completion: nil)
    }
}
