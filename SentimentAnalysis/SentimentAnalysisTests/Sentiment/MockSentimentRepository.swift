//
//  MockSentimentRepository.swift
//  SentimentAnalysisTests
//
//  Created by Thales Frigo on 03/12/18.
//  Copyright © 2018 Thales Frigo. All rights reserved.
//

import Foundation
import Result
@testable import SentimentAnalysis

struct MockSentimentRepository: SentimentRepositoryType  {
    typealias Handler = SentimentRepositoryType.Handler
    
    let isSuccess: Bool
    
    func analyzeDocument(with content: String, then handler: @escaping Handler) {
        if isSuccess {
            let sentiment = SentimentFactory.makeSentiment()
            handler(.success(sentiment))
        } else {
            let error = AnyError(MockSentimentRepositoryError.default)
            handler(.failure(error))
        }
    }
}

enum MockSentimentRepositoryError: Error {
    case `default`
}
