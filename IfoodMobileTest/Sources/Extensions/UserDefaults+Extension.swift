//
//  UserDefaults+Extension.swift
//  IfoodMobileTest
//
//  Created by André Vieira on 09/06/19.
//  Copyright © 2019 André Vieira. All rights reserved.
//

import KeychainSwift

public protocol UserDefaultKey {
    var name: String { get }
}

extension RawRepresentable where RawValue == String, Self: UserDefaultKey {
    public var name: String {
        return self.rawValue
    }
}

public enum TokenKey: String, UserDefaultKey {
    case twitterAccessToken
}

extension UserDefaults {
    public static func save(_ token: String) {
        KeychainSwift().set(token, forKey: TokenKey.twitterAccessToken)
    }
    public static func getToken() -> String {
        return KeychainSwift().get(TokenKey.twitterAccessToken)
    }
}

extension KeychainSwift {
    func get(_ key: UserDefaultKey) -> String {
        return self.get(key.name) ?? ""
    }
    func set(_ object: String, forKey key: UserDefaultKey) {
        self.set(object, forKey: key.name)
    }
}
