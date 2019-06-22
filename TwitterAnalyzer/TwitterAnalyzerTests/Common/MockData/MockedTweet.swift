//
//  MockedTweet.swift
//  TwitterAnalyzerTests
//
//  Created by Bruno Vieira on 21/06/19.
//  Copyright © 2019 Bruno Vieira. All rights reserved.
//

import Foundation
@testable import TwitterAnalyzer

extension Tweet {
    static let tweetJson = """
    {
        "full_text": "mocked tweet text",
        "created_at": "Thu Apr 06 15:28:43 +0000 2017"
    }
    """
    
    static func mockedTweet() -> Tweet? {
        guard let tweet = try? JSONDecoder().decode(Tweet.self, from: Data(tweetJson.utf8)) else {
            return nil
        }
        return tweet
    }
    
    static func mockedTweetList() -> [Tweet] {
        guard let tweet = mockedTweet() else {
            return []
        }
        return [tweet, tweet, tweet]
    }
}
