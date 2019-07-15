//
//  TweetMock.swift
//  twitter-moodTests
//
//  Created by Dennis Merli on 7/11/19.
//  Copyright © 2019 Dennis Merli. All rights reserved.
//

import Foundation

class TweetMock {
    class func loadTweetMock() -> Data {
        do {
            if let tweetsMock = Bundle(for: TweetMock.self).url(forResource: "twitter_response", withExtension: "json") {
                let data = try Data(contentsOf: tweetsMock )
                return data
            } else {
                return Data()
            }
        } catch {
            return Data()
        }
    }
}
