//
//  AuthModel.swift
//  SentimentAnalysis
//
//  Created by Thales Frigo on 28/11/18.
//  Copyright © 2018 Thales Frigo. All rights reserved.
//

import Foundation

struct Auth: Codable {
    let token: String
    
    init(token: String) {
        self.token = token
    }
    
    enum CodingKeys: String, CodingKey {
        case token = "access_token"
    }
}
