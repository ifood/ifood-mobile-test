//
//  StringConstants.swift
//  TwitterSentiments
//
//  Created by Marina Azevedo on 14/07/19.
//  Copyright © 2019 Marina Azevedo. All rights reserved.
//

import Foundation

internal enum LocalizedConstants {
    
    internal enum Home {
        
        internal enum CouldNotLoadUserAlert {
            /// Could not load user. Try another username!
            internal static let message = LocalizedConstants.localize("Localizable", "home.could_not_load_user_alert.message")
            /// OK
            internal static let ok = LocalizedConstants.localize("Localizable", "home.could_not_load_user_alert.OK")
            /// Invalid User
            internal static let title = LocalizedConstants.localize("Localizable", "home.could_not_load_user_alert.title")
        }
    }
    
    internal enum TweetSentiment {
        /// Couldn't load tweet sentiment. Try again later!
        internal static let errorMessage = LocalizedConstants.localize("Localizable", "tweet_sentiment.error_message")
        /// This is a happy tweet!
        internal static let happyMessage = LocalizedConstants.localize("Localizable", "tweet_sentiment.happy_message")
        /// This is a normal tweet!
        internal static let neutralMessage = LocalizedConstants.localize("Localizable", "tweet_sentiment.neutral_message")
        /// This is a sad tweet!
        internal static let sadMessage = LocalizedConstants.localize("Localizable", "tweet_sentiment.sad_message")
    }
    
    internal enum Tweets {
        /// Tweets not found!
        internal static let tweetsNotFound = LocalizedConstants.localize("Localizable", "tweets.tweets_not_found")
        /// Select one tweet to analyse its sentiment.
        internal static let tweetsSectionMessage = LocalizedConstants.localize("Localizable", "tweets.tweets_section_message")
        /// @%@ has not tweeted yet.
        internal static func userHasNotTweetedYet(_ p1: String) -> String {
            return LocalizedConstants.localize("Localizable", "tweets.user_has_not_tweeted_yet", p1)
        }
        /// User has not tweeted yet.
        internal static let userHasNotTweetedYetDefault = LocalizedConstants.localize("Localizable", "tweets.user_has_not_tweeted_yet_default")
    }
}


extension LocalizedConstants {
    private static func localize(_ table: String, _ key: String, _ args: CVarArg...) -> String {
        let format = NSLocalizedString(key, tableName: table, bundle: Bundle(for: BundleToken.self), comment: "")
        return String(format: format, locale: Locale.current, arguments: args)
    }
}

private final class BundleToken {}
