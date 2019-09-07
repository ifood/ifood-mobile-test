//
//  UserDefaults+Extensions.swift
//  ifood-mobile-test
//
//  Created by Jean Vinge on 25/12/18.
//  Copyright © 2018 Jean Vinge. All rights reserved.
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
