//
//  AcessToken.swift
//  TwitterSentiments
//
//  Created by Marina Azevedo on 14/07/19.
//  Copyright © 2019 Marina Azevedo. All rights reserved.
//

import Foundation

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
