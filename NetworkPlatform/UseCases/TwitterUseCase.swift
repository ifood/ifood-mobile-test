//
//  TwitterUserCase.swift
//  ifood-mobile-test
//
//  Created by Jean Vinge on 26/12/18.
//  Copyright © 2018 Jean Vinge. All rights reserved.
//

import RxSwift
import Domain

public struct TwitterUseCase: Domain.TwitterUseCase {
    
    public init() {
        
    }
    
    public func ensureAuthentication() -> Observable<String> {
        let accessToken = UserDefaults.getToken()
        return !accessToken.isEmpty ? .just(accessToken) : authenticate()
    }

    public func authenticate() -> Observable<String> {
        return Service().request(endpoint: TwitterTargetType.accessToken).map(TwitterToken.self).map { $0.accessToken }.do(onSuccess: UserDefaults.save).asObservable()
    }

    public func user(_ username: String) -> Observable<TwitterUser> {
        return ensureAuthentication().flatMapLatest { _ in Service().request(endpoint: TwitterTargetType.user(username: username)).map(TwitterUser.self)
        }
    }

    public func latestTweets(from user: TwitterUser) -> Observable<[Tweet]> {
        return ensureAuthentication().flatMapLatest {  _ in Service().request(endpoint: TwitterTargetType.latestTweets(username: user.screenName)).map([Tweet].self)
        }
    }
}
