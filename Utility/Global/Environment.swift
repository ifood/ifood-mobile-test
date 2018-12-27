//
//  Environment.swift
//  matchAcesso
//
//  Created by Jean Vinge on 20/08/2018.
//  Copyright © 2018 Atlas Politico. All rights reserved.
//

import Foundation

public enum EnvironmentType: String {
    case development
    case production
    case test
    case uiTest
}

public struct Environment {

    // MARK: Var

    private static var infoDictionary: JSON? {
        return Bundle.main.infoDictionary
    }

    public static var bundleId: String {
        guard let url = infoDictionary?["PRODUCT_BUNDLE_IDENTIFIER"] as? String else { fatalError("BundleId can't be nil") }
        return url
    }

    public static var env: EnvironmentType {
        guard let env = infoDictionary?["Environment"] as? String else {
            return .development
        }
        return self.env(text: env)
    }

    public static var currentLanguage: String {
        return NSLocale.current.languageCode ?? ""
    }

    private static func env(text: String) -> EnvironmentType {
        switch text {
        case EnvironmentType.development.rawValue:
            return .development
        case EnvironmentType.production.rawValue:
            return .production
        case EnvironmentType.test.rawValue:
            return .test
        case EnvironmentType.uiTest.rawValue:
            return .uiTest
        default:
            return .development
        }
    }
}
