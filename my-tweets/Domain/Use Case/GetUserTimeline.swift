//
//  GetUserTimeline.swift
//  my-tweets
//
//  Created by Gabriel Catice on 27/11/18.
//  Copyright © 2018 Gabriel Catice. All rights reserved.
//

import Foundation
import RxSwift

public extension GetUserTimeline {
    public struct Request {
        public let screenName: String
    }
}

public struct GetUserTimeline {
    let executorScheduler: ImmediateSchedulerType
    let postExecutionScheduler: ImmediateSchedulerType
    
    let controller: UserController
    let sentimentController: SentimentController
}

extension GetUserTimeline: SingleUseCase {
    func execute(request: Request?) -> Single<[Tweet]> {
        guard let screenName = request?.screenName else { return Single.error(DomainError.missingRequest)}
        return self.controller.getTimeline(screenName: screenName).flatMap { (tweets) in
            return Observable.from(tweets).flatMap { (tweet) in
                self.sentimentController.analyzeSentiment(text: tweet.sentence?.text ?? "").map {
                    Tweet(id: tweet.id, sentence: $0, createdDate: tweet.createdDate, user: tweet.user)
                }
                }.toArray().asSingle()
        }
    }
}
