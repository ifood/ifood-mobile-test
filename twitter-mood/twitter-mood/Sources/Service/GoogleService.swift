//
//  GoogleService.swift
//  twitter-mood
//
//  Created by Dennis Merli on 7/7/19.
//  Copyright © 2019 Dennis Merli. All rights reserved.
//

import Foundation
import RxSwift
import Moya

protocol GoogleServiceType {
    func moodForTweet(_ tweet: String?) -> Single<TweetMood>
}

final class GoogleService: BaseNetworkService<GoogleProvider>, GoogleServiceType {
    
    func moodForTweet(_ tweet: String?) -> Single<TweetMood> {
        guard let tweet = tweet else { return Single.error(CustomError.nilParameter(parameter: "Tweet"))}
        return provider.rx
            .request(.moodForTweet(tweet))
            .flatMap({ (response) -> Single<TweetMood> in
                do {
                    try _ = response.filterSuccessfulStatusCodes()
                    let tweetMood = try JSONDecoder().decode(TweetMood.self, from: response.data)
                    return Single.just(tweetMood)
                } catch {
                    return Single.error(NetworkManager.shared.handleMoyaError(error, response: response))
                }
            })
    }
}

