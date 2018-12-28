//
//  TwitterUseCaseMock.swift
//  TwitterSentimentTests
//
//  Created by Jean Vinge on 28/12/18.
//  Copyright © 2018 Jean Vinge. All rights reserved.
//

import Foundation
import Utility
import Domain
import RxSwift

enum TwitterMock: String, MockFile {
    case findUser200
    case findUser404
    case tweetList200
    case tweetList404
    case token200
    case token404
}

class TwitterUseCaseMock: TwitterUseCase {
    
    // MARK: Var
    
    var statusCode: StatusCode
    
    var ensureAuthenticationCalled = false
    var authenticateCalled = false
    var userMethodCalled = false
    var latestTweetsCalled = false
    
    // MARK: Int
    
    init(_ statusCode: StatusCode) {
        self.statusCode = statusCode
    }
    
    func ensureAuthentication() -> Observable<String> {
        self.ensureAuthenticationCalled = true
        switch statusCode {
        case .success:
            return .just(decode(TwitterToken.self, from: TwitterMock.token200, bundle: Bundle(for: type(of: self))).accessToken)
        case .failure:
            return .error(twitterError(TwitterMock.token404, bundle: Bundle(for: type(of: self))))
        }
    }
    
    func authenticate() -> Observable<String> {
        self.authenticateCalled = true
        return self.ensureAuthentication()
    }
    
    func user(_ username: String) -> Observable<TwitterUser> {
        self.userMethodCalled = true
        return self.authenticate().flatMap { _ -> Observable<TwitterUser> in
            switch self.statusCode {
            case .success:
                return .just(decode(TwitterUser.self, from: TwitterMock.findUser200, bundle: Bundle(for: type(of: self))))
            case .failure:
                return .error(twitterError(TwitterMock.findUser404, bundle: Bundle(for: type(of: self))))
            }
        }
    }
    
    func latestTweets(from user: TwitterUser) -> Observable<[Tweet]> {
        self.latestTweetsCalled = true
        return self.authenticate().flatMap({ _ -> Observable<[Tweet]> in
            switch self.statusCode {
            case .success:
                return .just(decode([Tweet].self, from: TwitterMock.tweetList200, bundle: Bundle(for: type(of: self))))
            case .failure:
                return .error(twitterError(TwitterMock.tweetList404, bundle: Bundle(for: type(of: self))))
            }
        })
    }
}
