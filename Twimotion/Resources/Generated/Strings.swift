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

  internal enum Tweets {
    /// Tweets not found!
    internal static let tweetsNotFound = L10n.tr("Localizable", "tweets.tweets_not_found")
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
