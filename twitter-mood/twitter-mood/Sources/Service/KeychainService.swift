//
//  KeychainService.swift
//  twitter-mood
//
//  Created by Dennis Merli on 7/9/19.
//  Copyright © 2019 Dennis Merli. All rights reserved.
//

import KeychainAccess

protocol KeychainServiceType {
    func setData(_ value: Data, key: String)  throws
    func set(_ value: String, key: String)  throws
    func get(_ key: String)  throws -> String?
    func getData(_ key: String)  throws -> Data?
    func remove(_ key: String)  throws
    func removeAll()  throws
    func contains(_ key: String) throws -> Bool
}

public final class KeychainService: KeychainServiceType {
    var keychain: Keychain
    
    init(service: String) {
        self.keychain = Keychain(service: service)
    }
    
    func setData(_ value: Data, key: String) throws {
        try keychain.set(value, key: key)
    }
    
    func set(_ value: String, key: String) throws {
        try keychain.set(value, key: key)
    }
    
    func get(_ key: String) throws -> String? {
        return try keychain.get(key)
    }
    
    func getData(_ key: String) throws -> Data? {
        return try keychain.getData(key)
    }
    
    func remove(_ key: String) throws {
        try keychain.remove(key)
    }
    
    func removeAll() throws {
        try keychain.removeAll()
    }
    
    func contains(_ key: String) throws -> Bool {
        return try keychain.contains(key)
    }
}

