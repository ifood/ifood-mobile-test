// Generated using SwiftGen, by O.Halligon — https://github.com/SwiftGen/SwiftGen

import Foundation

// swiftlint:disable superfluous_disable_command
// swiftlint:disable file_length

// swiftlint:disable explicit_type_interface identifier_name line_length nesting type_body_length type_name
internal enum Localization {
  /// Failed to load data. Please make sure you have an active network connection.
  internal static let errorViewFailedToLoadDataMessage = Localization.tr("Localizable", "error_view_failed_to_load_data_message")
  /// Try again
  internal static let errorViewRetryButtonTitle = Localization.tr("Localizable", "error_view_retry_button_title")
}
// swiftlint:enable explicit_type_interface identifier_name line_length nesting type_body_length type_name

extension Localization {
  private static func tr(_ table: String, _ key: String, _ args: CVarArg...) -> String {
    let format = NSLocalizedString(key, tableName: table, bundle: Bundle(for: BundleToken.self), comment: "")
    return String(format: format, locale: Locale.current, arguments: args)
  }
}

private final class BundleToken {}
