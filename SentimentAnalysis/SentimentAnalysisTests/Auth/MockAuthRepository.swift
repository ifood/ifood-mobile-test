//
//  MockAuthRepository.swift
//  SentimentAnalysisTests
//
//  Created by Thales Frigo on 03/12/18.
//  Copyright © 2018 Thales Frigo. All rights reserved.
//

import Foundation
import Result
@testable import SentimentAnalysis

struct MockAuthRepository: AuthRepositoryType  {
    typealias Handler = AuthRepositoryType.Handler
    
    let isSuccess: Bool
    
    func authenticate(then handler: @escaping Handler) {
        if isSuccess {
            let auth = AuthFactory.makeAuth()
            handler(.success(auth))
        } else {
            let error = AnyError(MockAuthRepositoryError.default)
            handler(.failure(error))
        }
    }
}

enum MockAuthRepositoryError: Error {
    case `default`
}

