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
        interactor.fetchTweets()
    }
}

extension TweetListPresenter: TweetListInteractorOutputProtocol {
    
    func tweetsFetched(_ tweets: [String]) {
        
    }
}
