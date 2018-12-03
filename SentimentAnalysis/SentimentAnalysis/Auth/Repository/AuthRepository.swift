//
//  AuthRepository.swift
//  SentimentAnalysis
//
//  Created by Thales Frigo on 01/12/18.
//  Copyright © 2018 Thales Frigo. All rights reserved.
//

import Foundation
import Result
import Moya

protocol AuthRepositoryType {
    typealias Handler = (Result<Auth, AnyError>) -> Void
    
    func authenticate(then handler: @escaping Handler)
}

struct AuthRepository: AuthRepositoryType {
    
    typealias Handler = AuthRepositoryType.Handler
    
    let provider: MoyaProvider<TwitterAPI>
    
    init() {
        self.provider = MoyaProvider<TwitterAPI>(
            plugins: [
                AccessTokenPlugin { TwitterAPIConfig.makeBasicAuthToken() }
            ]
        )
    }
    
    func authenticate(then handler: @escaping Handler) {
        provider.request(.auth) { (result) in
            do {
                let auth = try result.dematerialize().map(Auth.self)
                handler(.success(auth))
            } catch {
                handler(.failure(AnyError(error)))
            }
        }
    }
}
