//
//  TweetItemViewModel.swift
//  Twimotion
//
//  Created by Antony on 27/05/18.
//  Copyright © 2018 Twimotion. All rights reserved.
//

import Foundation

protocol TweetsItemViewModel {
    var profileImageUrl: URL? { get }
    var username: String { get }
    var name: String { get }
    var formattedDate: String { get }
    var text: String { get }
}

struct TweetItemViewModel {
    private let tweet: Tweet

    var profileImageUrl: URL? {
        return URL(string: tweet.user.profileImageUrlHttps)
    }

    var username: String { return "@\(tweet.user.screenName)" }

    var name: String { return tweet.user.name }

    var formattedDate: String {
        let fmt = DateFormatter(format: "dd/MM/yyyy")
        return fmt.string(from: tweet.createdAt)
    }

    var text: String { return tweet.text }

    init(tweet: Tweet) {
        self.tweet = tweet
    }
}
