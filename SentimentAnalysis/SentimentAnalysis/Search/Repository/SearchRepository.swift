//
//  SearchRepository.swift
//  SentimentAnalysis
//
//  Created by Thales Frigo on 02/12/18.
//  Copyright © 2018 Thales Frigo. All rights reserved.
//

import Foundation
import Result
import Moya

protocol SearchRepositoryType {
    typealias Handler = (Result<[Tweet], AnyError>) -> Void
    
    func search(with username: String, then handler: @escaping Handler)
}

struct SearchRepository: SearchRepositoryType {
    typealias Handler = SearchRepositoryType.Handler
    
    let provider: MoyaProvider<TwitterAPI>
    
    init(with auth: Auth) {
        let accessTokenPlugin = AccessTokenPlugin { () -> String in
            return auth.token
        }
        
        self.provider = MoyaProvider<TwitterAPI>(
            plugins: [accessTokenPlugin]
        )
    }
    
    func search(with username: String, then handler: @escaping Handler) {
        provider.request(.search(username: username)) { (result) in
            do {
                let tweets = try result.dematerialize().map([Tweet].self)
                handler(.success(tweets))
            } catch {
                handler(.failure(AnyError(error)))
            }
        }
    }
}
