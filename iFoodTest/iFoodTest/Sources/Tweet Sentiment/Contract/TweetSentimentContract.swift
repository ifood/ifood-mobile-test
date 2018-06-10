//
//  TweetSentimentContract.swift
//  iFoodTest
//
//  Created by Mauricio Cesar Maniglia Junior on 09/06/18.
//  Copyright © 2018 Mauricio Cesar Maniglia Junior. All rights reserved.
//

import UIKit

protocol TweetSentimentBuilderProtocol {
    static func assembleModule(forTweet tweet: Tweet) -> UIViewController
}

protocol TweetSentimentRouterProtocol {
    func closeTweetSentimentScreen()
}

protocol TweetSentimentViewProtocol: class {
    var presenter: TweetSentimentPresenterProtocol! { get set }
}

protocol TweetSentimentPresenterProtocol: class {
    weak var view: TweetSentimentViewProtocol? { get set }
    var router: TweetSentimentRouterProtocol! { get set }
    var interactor: TweetSentimentInteractorProtocol! { get set }
    
    func viewDidLoad()
    func didSelectClose()
}

protocol TweetSentimentInteractorProtocol: class {
    weak var output: TweetSentimentInteractorOutputProtocol! { get set }
    
    func fetchSentimentAnalysisForTweet(_ tweet: Tweet)
}

protocol TweetSentimentInteractorOutputProtocol: class {
    
}

protocol TweetSentimentStoreProtocol: class {
    func fetchSentimentAnalysisForTweet(_ tweet: Tweet)
}
