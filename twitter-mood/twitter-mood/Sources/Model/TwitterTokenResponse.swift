//
//  TwitterTokenResponse.swift
//  twitter-mood
//
//  Created by Dennis Merli on 7/9/19.
//  Copyright © 2019 Dennis Merli. All rights reserved.
//

import Foundation

struct TwitterTokenResponse: Codable {
    let tokenType: String?
    let accessToken: String?
    
    enum CodingKeys: String, CodingKey {
        case tokenType = "token_type"
        case accessToken = "access_token"
    }
}
