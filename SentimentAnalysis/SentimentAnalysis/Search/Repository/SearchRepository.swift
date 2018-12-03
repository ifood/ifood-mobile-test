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

enum SearchRepositoryError: LocalizedError {
    case userNotFound
    case userHasNoData
    case couldNotFetchData
    
    var failureReason: String? {
        return Localized(key: "SEARCH_FAILURE_REASON")
    }
    
    var recoverySuggestion: String? {
        return Localized(key: "SEARCH_RECOVERY_SUGGESTION")
    }
    
    var errorDescription: String? {
        switch self {
        case .userNotFound:
            return Localized(key: "SEARCH_USER_NOT_FOUND")
        case .userHasNoData:
            return Localized(key: "SEARCH_USER_HAS_NO_DATA")
        case .couldNotFetchData:
            return Localized(key: "SEARCH_COULD_NOT_FETCH_DATA")
        }
    }
    
    var imageName: String {
        switch self {
        case .userNotFound:
            return "neutral"
        case .userHasNoData:
            return "not_found"
        case .couldNotFetchData:
            return "sad"
        }
    }
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
            } catch let error as MoyaError {
                if error.response?.statusCode == 404 {
                    handler(.failure(AnyError(SearchRepositoryError.userNotFound)))
                } else {
                    handler(.failure(AnyError(SearchRepositoryError.couldNotFetchData)))
                }
            } catch {
                handler(.failure(AnyError(error)))
            }
        }
    }
}
