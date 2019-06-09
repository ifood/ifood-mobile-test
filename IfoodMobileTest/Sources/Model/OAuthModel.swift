//
//  OAuthModel.swift
//  IfoodMobileTest
//
//  Created by André Vieira on 09/06/19.
//  Copyright © 2019 André Vieira. All rights reserved.
//

import Foundation

struct OAuthModel: Codable {
    var tokenType: String?
    var accessToken: String?
    
    public enum CodingKeys: String, CodingKey {
        case tokenType = "token_type"
        case accessToken = "access_token"
    }
}
