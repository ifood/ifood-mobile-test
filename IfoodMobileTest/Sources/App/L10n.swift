// swiftlint:disable all
// Generated using SwiftGen — https://github.com/SwiftGen/SwiftGen

import Foundation

// swiftlint:disable superfluous_disable_command
// swiftlint:disable file_length

// MARK: - Strings

// swiftlint:disable explicit_type_interface function_parameter_count identifier_name line_length
// swiftlint:disable nesting type_body_length type_name
internal enum L10n {

  internal enum DefaultText {
    /// cancel
    internal static let cancel = L10n.tr("Localizable", "default-text.cancel")
    /// Error
    internal static let error = L10n.tr("Localizable", "default-text.error")
    /// Find
    internal static let find = L10n.tr("Localizable", "default-text.find")
    /// Sorry an error occurred. \n Please try again
    internal static let genericError = L10n.tr("Localizable", "default-text.generic-error")
    /// Ok
    internal static let ok = L10n.tr("Localizable", "default-text.ok")
    /// You are offline or with problem in your internet
    internal static let withoutInternet = L10n.tr("Localizable", "default-text.without-internet")
  }

  internal enum FindUser {
    /// Sorry, we can't get a Access Token. \n Please, try another username.
    internal static let tokenError = L10n.tr("Localizable", "find-user.token-error")
    /// Tell me who you want find
    internal static let twitterMessage = L10n.tr("Localizable", "find-user.twitter-message")
    /// We couldn't find this user. \n Please, try another username.
    internal static let userNotFound = L10n.tr("Localizable", "find-user.user-not-found")
  }
}
// swiftlint:enable explicit_type_interface function_parameter_count identifier_name line_length
// swiftlint:enable nesting type_body_length type_name

// MARK: - Implementation Details

extension L10n {
  private static func tr(_ table: String, _ key: String, _ args: CVarArg...) -> String {
    // swiftlint:disable:next nslocalizedstring_key
    let format = NSLocalizedString(key, tableName: table, bundle: Bundle(for: BundleToken.self), comment: "")
    return String(format: format, locale: Locale.current, arguments: args)
  }
}

private final class BundleToken {}
