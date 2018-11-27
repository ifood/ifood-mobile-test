//
//  AnalyzeSentiment.swift
//  my-tweets
//
//  Created by Gabriel Catice on 27/11/18.
//  Copyright © 2018 Gabriel Catice. All rights reserved.
//

import Foundation
import RxSwift

public extension AnalyzeSentiment {
    public struct Request {
        public let tweets: [Tweet]
    }
}

public struct AnalyzeSentiment {
    let executorScheduler: ImmediateSchedulerType
    let postExecutionScheduler: ImmediateSchedulerType
    
    let controller: SentimentController
}

extension AnalyzeSentiment: SingleUseCase {
    func execute(request: Request?) -> Single<[Tweet]> {
        guard let tweets = request?.tweets else { return Single.error(DomainError.missingRequest)}
        return Observable.from(tweets).flatMap { (tweet) in
            self.controller.analyzeSentiment(text: tweet.sentence?.text ?? "").map {
                Tweet(id: tweet.id, sentence: $0, createdDate: tweet.createdDate)
            }
        }.toArray().asSingle()
    }
}
