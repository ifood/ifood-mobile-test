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
    
}

protocol TweetListViewProtocol: class {
    var presenter: TweetListPresenterProtocol! { get set }
}

protocol TweetListPresenterProtocol: class {
    weak var view: TweetListViewProtocol? { get set }
    var router: TweetListRouterProtocol! { get set }
    var interactor: TweetListInteractorProtocol! { get set }
    
    func viewDidLoad()
}

protocol TweetListInteractorProtocol: class {
    weak var output: TweetListInteractorOutputProtocol! { get set }
    
    func fetchTweets()
}

protocol TweetListInteractorOutputProtocol: class {
    func tweetsFetched(_ tweets: [String])
}

protocol TweetListStoreProtocol: class {
    func fetchTweets(completion: @escaping ([Tweet]?, Error?) -> ())
}
