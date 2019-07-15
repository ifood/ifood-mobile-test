//
//  TwitterAPIResponse.swift
//  TwitterSentiments
//
//  Created by Marina Azevedo on 14/07/19.
//  Copyright © 2019 Marina Azevedo. All rights reserved.
//

import Foundation

import RxSwift

protocol TwitterDataSourceType {
    func getUser(username: String) -> Observable<User>
    func getLatestTweets(from user: User) -> Observable<[Tweet]>
}

enum TwitterError: Error {
    case authenticationError
    case couldNotLoadUser
    case couldNotLoadTweets
}

class TwitterDataSource {
    
    lazy var twitterApi = HttpService<TwitterAPI>()
    
    private func ensureAuthentication() -> Observable<String> {
        let accessToken = TwitterAccessToken.loadAccessToken()
        if !accessToken.isEmpty {
            return Observable.just(accessToken)
        }
        return authenticate()
    }
    
    private func authenticate() -> Observable<String> {
        return twitterApi.rx
            .request(TwitterAPI.getAccessToken)
            .map(TwitterAccessToken.self)
            .map { $0.accessToken }
            .do(onSuccess: TwitterAccessToken.saveToken)
            .asObservable()
            .catchError { _ in Observable.error(TwitterError.authenticationError) }
    }
}

extension TwitterDataSource: TwitterDataSourceType {
    func getUser(username: String) -> Observable<User> {
        return ensureAuthentication()
            .flatMapLatest { [weak twitterApi] _ -> Single<User> in
                guard let api = twitterApi else { fatalError("twitterApi should not be nil") }
                return api.rx
                    .request(.getUser(username: username))
                    .map(User.self)
                    .catchError { _ in Single.error(TwitterError.couldNotLoadUser) }
        }
    }
    
    func getLatestTweets(from user: User) -> Observable<[Tweet]> {
        return ensureAuthentication()
            .flatMapLatest { [weak twitterApi] _ -> Single<[Tweet]> in
                guard let api = twitterApi else { fatalError("twitterApi should not be nil") }
                return api.rx
                    .request(.getLastestTweets(username: user.screenName))
                    .map([Tweet].self)
                    .catchError { _ in Single.error(TwitterError.couldNotLoadTweets) }
        }
    }
}

