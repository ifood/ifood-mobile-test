//
//  Localization.swift
//  iFoodTest
//
//  Created by Mauricio Cesar Maniglia Junior on 10/06/18.
//  Copyright © 2018 Mauricio Cesar Maniglia Junior. All rights reserved.
//

import Foundation

enum Localization {
    
    enum TwitterUser {
        static let homeTitle = "HOME_TITLE".localized()
        static let listTweetsButtonTitle = "LIST_TWEETS_BUTTON_TITLE".localized()
    }
    
    enum TweetList {
        static let invalidTwitterUserErrorTitle = "INVALID_TWITTER_USER_ERROR_TITLE".localized()
        static let failedLoadTwitterUserErrorTItle = "FAILED_LOAD_TWITTER_USER_ERROR_TITLE".localized()
        static let tweetsNotFoundErrorTitle = "TWEETS_NOT_FOUND_ERROR_TITLE".localized()
    }
    
    enum TweetSentiment {
        static let failedLoadTweetSentimentErrorTitle = "FAILED_LOAD_TWEET_SENTIMENT_ERROR_TITLE".localized()
    }
}
