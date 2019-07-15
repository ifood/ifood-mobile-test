//
//  AccessToken.swift
//  twitter-mood
//
//  Created by Dennis Merli on 7/9/19.
//  Copyright © 2019 Dennis Merli. All rights reserved.
//

import Foundation

enum TokenType: String, Codable {
    case jwt
    case oauth
    case other
}

struct AccessToken: Codable {
    var accessToken: String
    var tokenType: TokenType
    var refreshToken: String?
    var expirationDate: Date?
    var issueDate: Date?
    var scope: String?
    var useRefresh: Bool = false
    
    init(tokenType: TokenType, accessToken: String, scope: String?, refreshToken: String?) {
        self.accessToken = accessToken
        self.tokenType = tokenType
        self.scope = scope
        self.refreshToken = refreshToken
    }
}

