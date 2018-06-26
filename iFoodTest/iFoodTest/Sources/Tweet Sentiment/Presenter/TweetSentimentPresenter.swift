//
//  TweetSentimentPresenter.swift
//  iFoodTest
//
//  Created by Mauricio Cesar Maniglia Junior on 09/06/18.
//  Copyright © 2018 Mauricio Cesar Maniglia Junior. All rights reserved.
//

import UIKit

final class TweetSentimentPresenter: TweetSentimentPresenterProtocol {
    
    var view: TweetSentimentViewProtocol?
    var router: TweetSentimentRouterProtocol!
    var interactor: TweetSentimentInteractorProtocol!
    var tweet: Tweet!
    
    func viewDidLoad() {
        interactor.fetchSentimentAnalysisForTweet(tweet)
    }
    
    func didSelectClose() {
        router.closeTweetSentimentScreen()
    }
}

extension TweetSentimentPresenter: TweetSentimentInteractorOutputProtocol {
    func textSentimentFetched(_ text: TextSentiment) {
        let emoji: String
        let color: UIColor
        
        switch text.sentiment {
        case .happy:
            emoji = "😃"
            color = .happy()
        case .neutral:
            emoji = "😐"
            color = .neutral()
        case .sad:
            emoji = "😔"
            color = .sad()
        }
        
        let tweetSentiment = TweetSentiment(emoji: emoji, color: color, tweet: tweet.text)
        view?.showTweetSentiment(tweetSentiment)
    }
    
    func presentError(_ error: String) {
        self.view?.displayErrorMessage(error)
    }
}
