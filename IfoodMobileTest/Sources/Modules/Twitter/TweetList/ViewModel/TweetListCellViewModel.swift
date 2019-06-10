//
//  TweetListCellViewModel.swift
//  IfoodMobileTest
//
//  Created by André Vieira on 10/06/19.
//  Copyright © 2019 André Vieira. All rights reserved.
//

import Foundation

protocol TweetListCellViewModelOutput {
    var tweetText: String? { get }
    var imgProfile: URL? { get }
    var tweetDate: String? { get }
}

protocol TweetListCellViewModelInput {}

final class TweetListCellViewModel: TweetListCellViewModelOutput, TweetListCellViewModelInput {
    
    var tweetText: String? {
        return tweet.text
    }
    
    var imgProfile: URL? {
        return tweet.user?.profileImageURL
    }
    
    var tweetDate: String? {
        guard let date = DateFormatter.EEEMMMddHHmmssZyyyy.date(from: tweet.createdAt ?? "") else {
            return ""
        }
        return DateFormatter.ddMMyyyyHHmm.string(from: date)
    }
    
    private var tweet: TweetModel
    
    init(tweet: TweetModel) {
        self.tweet = tweet
    }
}
