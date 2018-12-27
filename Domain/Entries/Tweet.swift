//
//  Tweet.swift
//  ifood-mobile-test
//
//  Created by Jean Vinge on 26/12/18.
//  Copyright © 2018 Jean Vinge. All rights reserved.
//

import Foundation
import Utility

public struct Tweet: Codable {
    
    // MARK: Var
    
    enum CodingKeys: String, CodingKey {
        case id = "id_str"
        case text
        case user
        case createdAt = "created_at"
    }
    
    public var id: String
    public var text: String
    public var user: TwitterUser
    public var createdAt: Date
    
    // MARK: Init
    
    public init(from decoder: Decoder) throws {
        let container = try decoder.container(keyedBy: CodingKeys.self)
        id = try container.decode(String.self, forKey: .id)
        text = try container.decode(String.self, forKey: .text)
        user = try container.decode(TwitterUser.self, forKey: .user)
        
        let dateString = try container.decode(String.self, forKey: .createdAt)
        guard let date = DateFormatter.twitterDate.date(from: dateString) else { throw Errors.codableError }
        createdAt = date
    }
}
