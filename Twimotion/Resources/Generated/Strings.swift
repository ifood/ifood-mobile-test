// Generated using SwiftGen, by O.Halligon — https://github.com/SwiftGen/SwiftGen

import Foundation

// swiftlint:disable superfluous_disable_command
// swiftlint:disable file_length

// swiftlint:disable explicit_type_interface identifier_name line_length nesting type_body_length type_name
internal enum L10n {

  internal enum Home {

    internal enum CouldNotLoadUserAlert {
      /// Could not load user. Try another username!
      internal static let message = L10n.tr("Localizable", "home.could_not_load_user_alert.message")
      /// OK
      internal static let ok = L10n.tr("Localizable", "home.could_not_load_user_alert.OK")
      /// Invalid User
      internal static let title = L10n.tr("Localizable", "home.could_not_load_user_alert.title")
    }
  }

  internal enum TweetSentiment {
    /// Couldn't load tweet sentiment. Try again later!
    internal static let errorMessage = L10n.tr("Localizable", "tweet_sentiment.error_message")
    /// This is a happy tweet!
    internal static let happyMessage = L10n.tr("Localizable", "tweet_sentiment.happy_message")
    /// This is a normal tweet!
    internal static let neutralMessage = L10n.tr("Localizable", "tweet_sentiment.neutral_message")
    /// This is a sad tweet!
    internal static let sadMessage = L10n.tr("Localizable", "tweet_sentiment.sad_message")
  }

  internal enum Tweets {
    /// Tweets not found!
    internal static let tweetsNotFound = L10n.tr("Localizable", "tweets.tweets_not_found")
    /// Select one tweet to analyse its sentiment.
    internal static let tweetsSectionMessage = L10n.tr("Localizable", "tweets.tweets_section_message")
    /// @%@ has not tweeted yet.
    internal static func userHasNotTweetedYet(_ p1: String) -> String {
      return L10n.tr("Localizable", "tweets.user_has_not_tweeted_yet", p1)
    }
    /// User has not tweeted yet.
    internal static let userHasNotTweetedYetDefault = L10n.tr("Localizable", "tweets.user_has_not_tweeted_yet_default")
  }
}
// swiftlint:enable explicit_type_interface identifier_name line_length nesting type_body_length type_name

extension L10n {
  private static func tr(_ table: String, _ key: String, _ args: CVarArg...) -> String {
    let format = NSLocalizedString(key, tableName: table, bundle: Bundle(for: BundleToken.self), comment: "")
    return String(format: format, locale: Locale.current, arguments: args)
  }
}

private final class BundleToken {}
