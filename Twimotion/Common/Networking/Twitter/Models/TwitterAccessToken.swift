//
//  TwitterAccessToken.swift
//  Twimotion
//
//  Created by Antony on 26/05/18.
//  Copyright © 2018 Twimotion. All rights reserved.
//

import Foundation
import SwiftKeychainWrapper

struct TwitterAccessToken: Decodable {
    let tokenType: String
    let accessToken: String
}

extension TwitterAccessToken {
    static func saveToken(_ token: String) {
        KeychainWrapper.standard.set(token, forKey: "kTwitterAccessToken")
    }

    static func loadAccessToken() -> String {
        return KeychainWrapper.standard.string(forKey: "kTwitterAccessToken") ?? ""
    }
}
