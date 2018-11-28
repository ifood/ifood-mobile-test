//
//  AuthModel.swift
//  SentimentAnalysis
//
//  Created by Thales Frigo on 28/11/18.
//  Copyright © 2018 Thales Frigo. All rights reserved.
//

import Foundation

struct AuthModel: Codable {
    let token: String
    
    enum CodingKeys: String, CodingKey {
        case token = "access_token"
    }
}
