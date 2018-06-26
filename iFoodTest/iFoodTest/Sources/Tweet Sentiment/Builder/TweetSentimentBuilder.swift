//
//  TweetSentimentBuilder.swift
//  iFoodTest
//
//  Created by Mauricio Cesar Maniglia Junior on 09/06/18.
//  Copyright © 2018 Mauricio Cesar Maniglia Junior. All rights reserved.
//

import UIKit

final class TweetSentimentBuilder: TweetSentimentBuilderProtocol {
    
    static func assembleModule(forTweet tweet: Tweet) -> UIViewController {
        let storyboard = UIStoryboard(name: "TweetSentimentStoryboard", bundle: nil)
        let view = storyboard.instantiateViewController(withIdentifier: "TweetSentimentViewController") as! TweetSentimentViewController
        let router = TweetSentimentRouter()
        let presenter = TweetSentimentPresenter()
        let interactor = TweetSentimentInteractor(output: presenter)
        
        view.presenter = presenter
        presenter.view = view
        presenter.router = router
        presenter.tweet = tweet
        presenter.interactor = interactor
        interactor.output = presenter
        router.viewController = view
        
        return view
    }
}
