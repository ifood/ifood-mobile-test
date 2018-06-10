//
//  TweetListContract.swift
//  iFoodTest
//
//  Created by Mauricio Cesar Maniglia Junior on 07/06/18.
//  Copyright © 2018 Mauricio Cesar Maniglia Junior. All rights reserved.
//

import UIKit

protocol TweetListBuilderProtocol {
    static func assembleModule(forTwitterUser twitterUser: String) -> UIViewController
}

protocol TweetListRouterProtocol {
    func presentTweetSentimentForTweet(_ tweet: Tweet)
}

protocol TweetListViewProtocol: class {
    var presenter: TweetListPresenterProtocol! { get set }
    
    func displayTweets(_ tweets: [Tweet], forUser user: String)
    func displayErrorMessage(_ error: String)
}

protocol TweetListPresenterProtocol: class {
    weak var view: TweetListViewProtocol? { get set }
    var router: TweetListRouterProtocol! { get set }
    var interactor: TweetListInteractorProtocol! { get set }
    
    func viewDidLoad()
    func didSelectTweeet(_ tweet: Tweet)
}

protocol TweetListInteractorProtocol: class {
    weak var output: TweetListInteractorOutputProtocol! { get set }
    
    func fetchTweetsForUser(_ user: String)
}

protocol TweetListInteractorOutputProtocol: class {
    func tweetsFetched(_ tweets: [Tweet])
    func presentError(_ error: String)
}

protocol TweetListStoreProtocol: class {
    func fetchTweets(forUser user: String, completion: @escaping ([Tweet]?, TweetListStoreError?) -> ())
}
