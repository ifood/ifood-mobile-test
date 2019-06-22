//
//  TweetSentimentInteractor.swift
//  TwitterAnalyzer
//
//  Created by Bruno Vieira on 18/06/19.
//  Copyright © 2019 Bruno Vieira. All rights reserved.
//

import UIKit

protocol TweetSentimentBusinessLogic {
    func loadInformation(request: TweetSentiment.LoadInformation.Request)
}

protocol TweetSentimentDataStore {
    var sentiment: SentimentAnalysis? { get set }
}

class TweetSentimentInteractor: TweetSentimentBusinessLogic, TweetSentimentDataStore {
    var presenter: TweetSentimentPresentationLogic?
    var worker: TweetSentimentWorker?
    var sentiment: SentimentAnalysis?

    // MARK: Load Information
    func loadInformation(request: TweetSentiment.LoadInformation.Request) {
        guard let sentimentScore = sentiment?.analysis?.score else {
            return
        }
        var sentiment: Tweet.Sentiment = .NEUTRAL
        if sentimentScore < -0.25 {
            sentiment = .NEGATIVE
        } else if sentimentScore > 0.25 {
            sentiment = .POSITIVE
        }
        presenter?.presentLoadInformation(response: TweetSentiment.LoadInformation.Response(sentiment: sentiment))
    }
}
