//
//  MockSearchRepository.swift
//  SentimentAnalysisTests
//
//  Created by Thales Frigo on 03/12/18.
//  Copyright © 2018 Thales Frigo. All rights reserved.
//

import Foundation
import Result
@testable import SentimentAnalysis

struct MockSearchRepository: SearchRepositoryType  {
    typealias Handler = SearchRepositoryType.Handler
    
    let isSuccess: Bool
    
    func search(with username: String, maxId: Int64?, then handler: @escaping Handler) {
        if isSuccess {
            let tweets = TweetFactory.makeArray()
            handler(.success(tweets))
        } else {
            let error = AnyError(MockSearchRepositoryError.default)
            handler(.failure(error))
        }
    }
}

enum MockSearchRepositoryError: Error {
    case `default`
}
