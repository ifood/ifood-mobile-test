//
//  TweetListPresenter.swift
//  TwitterAnalyzer
//
//  Created by Bruno Vieira on 18/06/19.
//  Copyright © 2019 Bruno Vieira. All rights reserved.
//

import UIKit

protocol TweetListPresentationLogic {
    func presentLoadInformation(response: TweetList.LoadInformation.Response)
    func presentSelectTweet(response: TweetList.SelectTweet.Response)
    func presentError(response: TweetList.Error.Response)
}

class TweetListPresenter: TweetListPresentationLogic {
    private weak var viewController: TweetListDisplayLogic?
    
    init(viewController: TweetListDisplayLogic) {
        self.viewController = viewController
    }
    
    // MARK: Load Information
    
    func presentLoadInformation(response: TweetList.LoadInformation.Response) {
        var tweetViewModels = [TweetList.LoadInformation.ViewModel.Tweet]()
        var image: UIImage? = UIImage(named: "twitter_logo")
        if let tweets = response.user.tweets {
            for tweet in tweets {
                var detail = ""
                if let replyAccount = tweet.replyAccount {
                    detail = "In reply to @\(replyAccount)"
                }
                if let retweetName = tweet.retweet?.user?.account {
                    detail = "Retweet from @\(retweetName)"
                }
                
                var stringDate = "-"
                if let date = DateFormatter.fullDateFormatter().date(from: tweet.date ?? "") {
                    stringDate = DateFormatter.simplifiedDateFormatter().string(from: date)
                }
                
                tweetViewModels.append(TweetList.LoadInformation.ViewModel.Tweet(text: tweet.text ?? "-",
                                                                                 date: stringDate,
                                                                                 detail: detail))
            }
            if let data = response.data, let loadedImage = UIImage(data: data) {
                image = loadedImage
            }
        }
        let viewModel = TweetList.LoadInformation.ViewModel(user: "@\(response.user.account ?? "-")",
                                                            name: response.user.name ?? "-",
                                                            profileImage: image,
                                                            tweets: tweetViewModels)
        viewController?.displayLoadInformation(viewModel: viewModel)
    }
    
    // MARK: Select Tweet
    
    func presentSelectTweet(response: TweetList.SelectTweet.Response) {
        viewController?.displaySelectTweet(viewModel: TweetList.SelectTweet.ViewModel())
    }
    
    // MARK: Error
    
    func presentError(response: TweetList.Error.Response) {
        viewController?.displayError(viewModel: TweetList.Error.ViewModel(errorTitle: "Ops",
                                                                          errorMessage: response.error.description))
    }
}
