//
//  SentimentRepository.swift
//  SentimentAnalysis
//
//  Created by Thales Frigo on 02/12/18.
//  Copyright © 2018 Thales Frigo. All rights reserved.
//

import Foundation
import Result
import Moya

protocol SentimentRepositoryType {
    typealias Handler = (Result<Sentiment, AnyError>) -> Void
    
    func analyzeDocument(with content: String, then handler: @escaping Handler)
}

struct SentimentRepository: SentimentRepositoryType {
    typealias Handler = SentimentRepositoryType.Handler
    
    let provider: MoyaProvider<SentimentAPI>
    
    init() {
        self.provider = MoyaProvider<SentimentAPI>(plugins: [SentimentAuthPlugin(key: SentimentAPIConfig.makeApiKey())])
    }
    
    func analyzeDocument(with content: String, then handler: @escaping Handler) {
        provider.request(.analyzeDocument(content: content)) {(result) in
            do {
                let sentiment = try result.dematerialize().map(SentimentAnalyzerResponse.self).document
                handler(.success(sentiment))
            } catch {
                handler(.failure(AnyError(error)))
            }
        }
    }
}
