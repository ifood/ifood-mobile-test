//
//  AnalizerUseCase.swift
//  ifood-mobile-test
//
//  Created by Jean Vinge on 26/12/18.
//  Copyright © 2018 Jean Vinge. All rights reserved.
//

import RxSwift
import Domain

public struct TextAnalizerUseCase: Domain.TextAnalizerUseCase {
    public init() {
        
    }
    public func sentiment(text: String) -> Observable<Sentiment> {
        return Service().request(endpoint: TextAnalizerTargetType.analize(text: text)).map(DocumentSentimentPayload.self).map { $0.documentSentiment.sentiment }.asObservable()
    }
}
