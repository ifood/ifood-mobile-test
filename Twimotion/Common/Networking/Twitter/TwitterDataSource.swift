//
//  TwitterDataSource.swift
//  Twimotion
//
//  Created by Antony on 27/05/18.
//  Copyright © 2018 Twimotion. All rights reserved.
//

import Foundation
import RxSwift

protocol TwitterDataSourceType {
    func getUser(username: String) -> Observable<TwitterUser>
    func getLatestTweets(from user: TwitterUser) -> Observable<[Tweet]>
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
            .request(TwitterAPI.getAccessToken())
            .map(TwitterAccessToken.self)
            .map { $0.accessToken }
            .do(onSuccess: TwitterAccessToken.saveToken)
            .asObservable()
    }
}

// MARK: - TwitterDataSourceType
extension TwitterDataSource: TwitterDataSourceType {
    func getUser(username: String) -> Observable<TwitterUser> {
        return ensureAuthentication()
            .flatMapLatest { [weak twitterApi] _ -> Single<TwitterUser> in
                guard let api = twitterApi else { fatalError("twitterApi should not be nil") }
                return api.rx
                    .request(.getUser(username: username))
                    .map(TwitterUser.self)

            }
    }

    func getLatestTweets(from user: TwitterUser) -> Observable<[Tweet]> {
        return ensureAuthentication()
            .flatMapLatest { [weak twitterApi] _ -> Single<[Tweet]> in
                guard let api = twitterApi else { fatalError("twitterApi should not be nil") }
                return api.rx
                    .request(.getLastestTweets(username: user.screenName))
                    .map([Tweet].self)
            }
            .catchErrorJustReturn([])
    }
}

