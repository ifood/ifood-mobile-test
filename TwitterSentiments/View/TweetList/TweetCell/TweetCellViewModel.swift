//
//  TweetCellViewModel.swift
//  TwitterSentiments
//
//  Created by Marina Azevedo on 14/07/19.
//  Copyright © 2019 Marina Azevedo. All rights reserved.
//

import Foundation

protocol TweetItemViewModelType {
    var profileImageUrl: URL? { get }
    var username: String { get }
    var name: String { get }
    var formattedDate: String { get }
    var text: String { get }
}

struct TweetItemViewModel: TweetItemViewModelType {
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
