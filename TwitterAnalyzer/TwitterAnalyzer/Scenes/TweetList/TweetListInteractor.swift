//
//  TweetListInteractor.swift
//  TwitterAnalyzer
//
//  Created by Bruno Vieira on 18/06/19.
//  Copyright © 2019 Bruno Vieira. All rights reserved.
//


protocol TweetListBusinessLogic {
    func loadInformation(request: TweetList.LoadInformation.Request)
    func selectTweet(request: TweetList.SelectTweet.Request)
}

protocol TweetListDataStore {
    var user: User? { get set }
    var selectedTweet: Tweet? { get set }
}

class TweetListInteractor: TweetListBusinessLogic, TweetListDataStore {
    var presenter: TweetListPresentationLogic?
    var worker: TweetListWorker?
    var user: User?
    var selectedTweet: Tweet?
    
    // MARK: Load Information
    func loadInformation(request: TweetList.LoadInformation.Request) {
        guard let user = user, let stringUrl = user.profilePictureUrl else {
            return
        }
        worker = TweetListWorker()
        worker?.requestUserProfileImage(from: stringUrl, success: { [weak self] (imageData) in
            self?.presenter?.presentLoadInformation(response: TweetList.LoadInformation.Response(user: user, data: imageData))
        }, failure: { [weak self] (_) in
            self?.presenter?.presentLoadInformation(response: TweetList.LoadInformation.Response(user: user, data: nil))
        })
    }
    
    // MARK: Select Tweet
    func selectTweet(request: TweetList.SelectTweet.Request) {
        worker = TweetListWorker()
        guard let user = user, let tweets = user.tweets else {
            return
        }
        if request.row < tweets.count {
            selectedTweet = tweets[request.row]
            worker?.analyzeTweet(text: selectedTweet?.text ?? "-", success: { [weak self] (analysis) in
                guard let tweet = self?.selectedTweet else {
                    return
                }
                self?.selectedTweet?.sentiment = analysis
                self?.presenter?.presentSelectTweet(response: TweetList.SelectTweet.Response(tweet: tweet))
            }, failure: { [weak self] (applicationError) in
                self?.presenter?.presentError(response: TweetList.Error.Response(error: applicationError))
            })
        }
    }
}
