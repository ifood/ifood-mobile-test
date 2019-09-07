//
//  TextAnalizerUseCaseMock.swift
//  TwitterSentimentTests
//
//  Created by Jean Vinge on 28/12/18.
//  Copyright © 2018 Jean Vinge. All rights reserved.
//

@testable import TwitterSentiment

import Domain
import RxSwift
import Utility

enum AnalizerMock: String, MockFile {
    case analizerNeutral
    case analizerHappy
    case analizerSad
    
    static func mock(from sentiment: Sentiment) -> AnalizerMock {
        switch sentiment {
        case .error:
            fatalError()
        case .happy:
            return .analizerHappy
        case .neutral:
            return .analizerNeutral
        case .sad:
            return .analizerSad
        }
    }
}

class TextAnalizerUseCaseMock: TextAnalizerUseCase {
    
    var sentimentCalled = false
    
    var sentiment: Sentiment
    
    init(sentiment: Sentiment) {
        self.sentiment = sentiment
    }
    
    func sentiment(text: String) -> Observable<Sentiment> {
        self.sentimentCalled = true
        return .just(decode(DocumentSentimentPayload.self, from: AnalizerMock.mock(from: sentiment), bundle: Bundle(for: type(of: self))).documentSentiment.sentiment)
    }
}
