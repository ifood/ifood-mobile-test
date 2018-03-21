//
//  AccessTokenDAO.swift
//  twitments
//
//  Created by Ezequiel on 08/03/18.
//  Copyright © 2018 Ezequiel. All rights reserved.
//

import Foundation
import KeychainSwift

class AccessTokenDAO: NSObject {

    static func saveToken(token: String?) {
        if let _token = token {
            let keychain = KeychainSwift()
            keychain.set(_token, forKey: "kAccessTokenDAO")
        }
    }

    static func removeToken() {

    }

    static func getToken() -> String {
        let keychain = KeychainSwift()
        guard let _key = keychain.get("kAccessTokenDAO") else {
            return ""
        }
        return _key
    }
}
