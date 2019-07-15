//
//  AccessTokenService.swift
//  twitter-mood
//
//  Created by Dennis Merli on 7/9/19.
//  Copyright © 2019 Dennis Merli. All rights reserved.
//

import Foundation

final class AccessTokenService {
    private var keychainService: KeychainServiceType
    
    init(keychainService: KeychainServiceType) {
        self.keychainService = keychainService
    }
    
    func saveAccessToken(_ accessToken: AccessToken) throws {
        try self.keychainService.set(accessToken.accessToken, key: "access_token")
        if let refreshToken = accessToken.refreshToken {
            try self.keychainService.set(refreshToken, key: "refresh_token")
        }
        try self.keychainService.set(accessToken.tokenType.rawValue, key: "token_type")
    }
    
    func loadAccessToken() throws -> AccessToken? {
        guard let accessToken = try self.keychainService.get("access_token"),
            let tokenType = try self.keychainService.get("token_type")
            else { return nil }
        let refreshToken = try self.keychainService.get("refresh_token")
        return AccessToken(tokenType: TokenType(rawValue: tokenType) ?? .other, accessToken: accessToken, scope: nil, refreshToken: refreshToken)
    }
    
    func deleteAccessToken() throws {
        try self.keychainService.remove("access_token")
        try self.keychainService.remove("token_type")
        try self.keychainService.remove("refresh_token")
    }
}
