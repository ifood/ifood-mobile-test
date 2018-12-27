// swiftlint:disable all
// Generated using SwiftGen, by O.Halligon — https://github.com/SwiftGen/SwiftGen

import Foundation

// swiftlint:disable superfluous_disable_command
// swiftlint:disable file_length

// MARK: - Strings

// swiftlint:disable explicit_type_interface function_parameter_count identifier_name line_length
// swiftlint:disable nesting type_body_length type_name
public enum L10n {

  public enum CellError {
    /// the cell can't be nil.
    public static let cantBeNil = L10n.tr("Localizable", "cell-error.cant-be-nil")
  }

  public enum CodableError {
    /// unknown codable error.
    public static let unknownError = L10n.tr("Localizable", "codable-error.unknown-error")
  }

  public enum DefaultText {
    /// cancel
    public static let cancel = L10n.tr("Localizable", "default-text.cancel")
    /// Error
    public static let error = L10n.tr("Localizable", "default-text.error")
    /// ok
    public static let ok = L10n.tr("Localizable", "default-text.ok")
  }

  public enum ErrorBaseUrl {
    /// base URL shouldn't be nil.
    public static let cantBeNil = L10n.tr("Localizable", "error-base-url.cant-be-nil")
  }

  public enum FindUser {
    /// search a twitter username.
    public static let textfieldPlaceholder = L10n.tr("Localizable", "find-user.textfield-placeholder")
    /// let's find out what user's are feeling.
    public static let titleLabel = L10n.tr("Localizable", "find-user.title-label")
    /// we couldn't find this user. \ntry another username.
    public static let userNotFound = L10n.tr("Localizable", "find-user.user-not-found")
  }

  public enum RequestError {
    /// couldn't load content.
    public static let couldNotLoad = L10n.tr("Localizable", "request-error.could-not-load")
    /// request didn't complete.
    public static let unknownError = L10n.tr("Localizable", "request-error.unknown-error")
  }

  public enum Sentiment {
    /// look's like this tweet is happy.
    public static let happyTweet = L10n.tr("Localizable", "sentiment.happy-tweet")
    /// look's like this tweet is neutral.
    public static let neutralTweet = L10n.tr("Localizable", "sentiment.neutral-tweet")
    /// look's like this tweet is sad.
    public static let sadTweet = L10n.tr("Localizable", "sentiment.sad-tweet")
  }

  public enum TwitterError {
    /// couldn't load tweet sentiment. \ntry again later.
    public static let couldNotLoadSentiment = L10n.tr("Localizable", "twitter-error.could-not-load-sentiment")
  }
}
// swiftlint:enable explicit_type_interface function_parameter_count identifier_name line_length
// swiftlint:enable nesting type_body_length type_name

// MARK: - Implementation Details

extension L10n {
  private static func tr(_ table: String, _ key: String, _ args: CVarArg...) -> String {
    let format = NSLocalizedString(key, tableName: table, bundle: Bundle(for: BundleToken.self), comment: "")
    return String(format: format, locale: Locale.current, arguments: args)
  }
}

private final class BundleToken {}
