//
//  TwitterAccessToken.swift
//  iFoodTest
//
//  Created by Mauricio Cesar Maniglia Junior on 08/06/18.
//  Copyright © 2018 Mauricio Cesar Maniglia Junior. All rights reserved.
//

import Foundation

struct TwitterAccessToken {
    static func saveToken(_ token: String) {
        KeychainWrapper.standard.set(token, forKey: "twitterAccessToken")
    }
    
    static func loadAccessToken() -> String {
        return KeychainWrapper.standard.string(forKey: "twitterAccessToken") ?? ""
    }
}
