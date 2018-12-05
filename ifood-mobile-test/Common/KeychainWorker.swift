//
//  KeychainWorker.swift
//  ifood-mobile-test
//
//  Created by Marcio Garcia on 04/12/18.
//  Copyright © 2018 oxltech.com. All rights reserved.
//

import KeychainAccess

enum Keys: String {
    case apiKey = "api_key"
    case apiSecret = "api_secret"
    case gcKey = "gc_key"
}

class KeychainWorker {
    
    var keychain: Keychain
    
    init(service: String) {
        keychain = Keychain(service: service)
    }
    
    func set(key: String, value: String) {
        keychain[key] = value
    }
    
    func get(key: String) -> String? {
        return keychain[key]
    }
    
    func remove(key: String) {
        try? keychain.remove(key)
    }
}
