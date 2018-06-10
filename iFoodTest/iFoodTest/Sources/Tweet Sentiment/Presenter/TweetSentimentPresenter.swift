//
//  TweetSentimentPresenter.swift
//  iFoodTest
//
//  Created by Mauricio Cesar Maniglia Junior on 09/06/18.
//  Copyright © 2018 Mauricio Cesar Maniglia Junior. All rights reserved.
//

import Foundation

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
    
}
