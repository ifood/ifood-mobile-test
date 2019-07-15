//
//  TwitterService.swift
//  twitter-mood
//
//  Created by Dennis Merli on 7/6/19.
//  Copyright © 2019 Dennis Merli. All rights reserved.
//

import Foundation
import RxSwift
import Moya

protocol TwitterServiceType {
    func fetchTweets(username: String, count: Int) -> Single<[Tweet]>
    func authorize() -> Single<TwitterTokenResponse>
}

final class TwitterService: BaseNetworkService<TwitterProvider>, TwitterServiceType {
    func fetchTweets(username: String, count: Int) -> Single<[Tweet]> {
        return provider.rx
            .request(.fetchTweets(username: username, count: count))
            .flatMap({ (response) -> Single<[Tweet]> in
                do {
                    try _ = response.filterSuccessfulStatusCodes()
                    let tweets = try JSONDecoder().decode([Tweet].self, from: response.data)
                    return Single.just(tweets)
                } catch {
                    return Single.error(NetworkManager.shared.handleMoyaError(error, response: response))
                }
            })
    }
    
    func authorize() -> Single<TwitterTokenResponse> {
        return provider.rx
            .request(.authorize)
            .flatMap({ (response) -> Single<TwitterTokenResponse> in
                do {
                    try _ = response.filterSuccessfulStatusCodes()
                    let token = try JSONDecoder().decode(TwitterTokenResponse.self, from: response.data)
                    return Single.just(token)
                } catch {
                    return Single.error(NetworkManager.shared.handleMoyaError(error, response: response))
                }
            })
    }
    
}

