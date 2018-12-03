//
//  Keychain.swift
//  Tink AB
//
//  Created by Lukas Lipka on 21/08/2018.
//  Copyright © 2018 Tink AB. All rights reserved.
//

import Foundation
import Security

private let kSecClassValue = String(kSecClass)
private let kSecAttrAccountValue = String(kSecAttrAccount)
private let kSecValueDataValue = String(kSecValueData)
private let kSecClassGenericPasswordValue = String(kSecClassGenericPassword)
private let kSecAttrServiceValue = String(kSecAttrService)
private let kSecMatchLimitValue = String(kSecMatchLimit)
private let kSecReturnDataValue = String(kSecReturnData)
private let kSecMatchLimitOneValue = String(kSecMatchLimitOne)

public struct Keychain {
    private init() {}

    public static func set(password: String, account: String, service: String) {
        guard let data = password.data(using: .utf8) else { return }
        let query: [String: Any] = [
            kSecClassValue: kSecClassGenericPasswordValue,
            kSecAttrServiceValue: service,
            kSecAttrAccountValue: account,
            kSecValueDataValue: data
        ]

        SecItemDelete(query as CFDictionary)
        SecItemAdd(query as CFDictionary, nil)
    }

    public static func get(account: String, service: String) -> String? {
        let query: [String: Any] = [
            kSecClassValue: kSecClassGenericPasswordValue,
            kSecAttrServiceValue: service,
            kSecAttrAccountValue: account,
            kSecReturnDataValue: kCFBooleanTrue,
            kSecMatchLimitValue: kSecMatchLimitOneValue
        ]

        var buffer: AnyObject?
        if SecItemCopyMatching(query as CFDictionary, &buffer) == errSecSuccess {
            if let data = buffer as? Data {
                return String(data: data, encoding: .utf8)
            }
        }

        return nil
    }

    public static func delete(account: String, service: String) {
        let query: [String: Any] = [
            kSecClassValue: kSecClassGenericPasswordValue,
            kSecAttrServiceValue: service,
            kSecAttrAccountValue: account
        ]

        SecItemDelete(query as CFDictionary)
    }
}
