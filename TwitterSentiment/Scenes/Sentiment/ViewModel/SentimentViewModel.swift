//
//  SentimentViewModel.swift
//  TwitterSentiment
//
//  Created by Jean Vinge on 27/12/18.
//  Copyright © 2018 Jean Vinge. All rights reserved.
//

import Foundation
import RxSwift
import RxCocoa
import Domain
import Utility

struct SentimentViewModel: ViewModel {
    
    // MARK: Var
    
    var router: RouterProvider
    var useCase: TextAnalizerUseCase
    var tweet: Tweet
    
    // MARK: Init
    
    init(tweet: Tweet, useCase: TextAnalizerUseCase, router: RouterProvider) {
        self.router = router
        self.useCase = useCase
        self.tweet = tweet
    }
    
    func transform(input: SentimentViewModel.Input) -> SentimentViewModel.Output {
        
        let activity = ActivityIndicator()
        
        let analize = input.trigger.map { self.tweet }.flatMapLatest { tweet -> Driver<SentimentOutput> in
            return self.useCase.sentiment(text: tweet.text).trackActivity(activity).map { sentimentFactory(sentiment: $0) }.asDriver(onErrorJustReturn: sentimentFactory(sentiment: .error))
        }
        
        let dismiss = input.onDismiss.flatMap { _ -> Driver<Void> in
            return self.router.transition(with: SentimentRouter.dismiss).asDriverOnErrorJustComplete()
        }
        
        return Output(showSentiment: analize, onDismiss: dismiss, fetching: activity.asDriver())
    }
}

extension SentimentViewModel {
    struct Input {
        var trigger: Driver<Void>
        var onDismiss: Driver<Void>
    }
    
    struct Output {
        var showSentiment: Driver<SentimentOutput>
        var onDismiss: Driver<Void>
        var fetching: Driver<Bool>
    }
}
