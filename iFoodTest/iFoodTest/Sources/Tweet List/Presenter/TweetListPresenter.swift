//
//  TweetListPresenter.swift
//  iFoodTest
//
//  Created by Mauricio Cesar Maniglia Junior on 07/06/18.
//  Copyright © 2018 Mauricio Cesar Maniglia Junior. All rights reserved.
//

import Foundation

final class TweetListPresenter: TweetListPresenterProtocol {
    
    var view: TweetListViewProtocol?
    var router: TweetListRouterProtocol!
    var interactor: TweetListInteractorProtocol!
    var twitterUser: String!
    
    func viewDidLoad() {
        interactor.fetchTweetsForUser(twitterUser)
    }
    
    func didSelectTweeet(_ tweet: Tweet) {
        router.presentTweetSentimentForTweet(tweet)
    }
}

extension TweetListPresenter: TweetListInteractorOutputProtocol {
    
    func tweetsFetched(_ tweets: [Tweet]) {
        self.view?.displayTweets(tweets, forUser:twitterUser)
    }
    
    func presentError(_ error: String) {
        self.view?.displayErrorMessage(error)
    }
}
