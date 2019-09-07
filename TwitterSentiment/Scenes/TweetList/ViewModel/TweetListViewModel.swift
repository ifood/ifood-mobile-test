//
//  TweetListViewModel.swift
//  TwitterSentiment
//
//  Created by Jean Vinge on 26/12/18.
//  Copyright © 2018 Jean Vinge. All rights reserved.
//

import Foundation
import RxSwift
import RxCocoa
import Domain
import Utility

struct TweetListViewModel: ViewModel {
    
    // MARK: Var
    
    var router: RouterProvider
    var useCase: TwitterUseCase
    var user: TwitterUser
    
    // MARK: Init
    
    init(user: TwitterUser, useCase: TwitterUseCase, router: RouterProvider) {
        self.user = user
        self.useCase = useCase
        self.router = router
    }
    
    func transform(input: Input) -> Output {
        
        let activityIndicator = ActivityIndicator()
        
        let loadList = input.trigger.map { self.user }.flatMapLatest { user -> Driver<[Tweet]> in
            return self.useCase.latestTweets(from: user).trackActivity(activityIndicator).do(onError: { error in _ =
                self.router.transition(with: TweetListRouter.error(error: error))}).asDriverOnErrorJustComplete()
            }.map { [TweetSectionModel(model: "", items: $0)] }

        let onTapTweet = input.onTapTweet.flatMapLatest { tweet -> Driver<Void> in
            return self.router.transition(with: TweetListRouter.analizeSentiment(tweet: tweet)).asDriverOnErrorJustComplete()
        }
        return Output(tweetList: loadList, onTapTweet: onTapTweet, title: .just(self.user.decoratedUserName), fetching: activityIndicator.asDriver())
    }
}

extension TweetListViewModel {
    struct Input {
        let trigger: Driver<Void>
        let onTapTweet: Driver<Tweet>
    }
    
    struct Output {
        let tweetList: Driver<[TweetSectionModel]>
        let onTapTweet: Driver<Void>
        let title: Driver<String>
        let fetching: Driver<Bool>
    }
}
