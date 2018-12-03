//
//  Keychain+AuthTokenStore.swift
//  SentimentAnalysis
//
//  Created by Thales Frigo on 02/12/18.
//  Copyright © 2018 Thales Frigo. All rights reserved.
//

import Foundation
import Keychain

protocol AuthTokenStore {
    func save(auth: Auth)
    func retrieve() -> Auth?
    
    func hasToken() -> Bool
}

extension AuthTokenStore {
    func hasToken() -> Bool {
        return retrieve() != nil
    }
}

struct KeychainTokenStore: AuthTokenStore {
    func save(auth: Auth) {
        Keychain.set(password: auth.token, account: "twitter", service: "api")
    }
    
    func retrieve() -> Auth? {
        guard let token = Keychain.get(account: "twitter", service: "api") else {
            return nil
        }
        
        return Auth(token: token)
    }
}
