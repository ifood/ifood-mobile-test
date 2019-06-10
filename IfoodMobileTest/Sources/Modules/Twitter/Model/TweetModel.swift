//
//  TweetModel.swift
//  IfoodMobileTest
//
//  Created by André Vieira on 09/06/19.
//  Copyright © 2019 André Vieira. All rights reserved.
//

import Foundation

struct TweetModel: Codable {
    var text: String?
    var id: Int64?
    var createdAt: String?
    var user: TwitterUser?
    
    public enum CodingKeys: String, CodingKey {
        case text
        case id
        case createdAt = "created_at"
        case user
    }
}
