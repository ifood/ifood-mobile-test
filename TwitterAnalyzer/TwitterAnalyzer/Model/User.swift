//
//  User.swift
//  TwitterAnalyzer
//
//  Created by Bruno Vieira on 18/06/19.
//  Copyright © 2019 Bruno Vieira. All rights reserved.
//

import Foundation

struct User: Codable {
    let name: String?
    let account: String?
    let profilePictureUrl: String?
    var tweets: [Tweet]?
    
    enum CodingKeys: String, CodingKey {
        case name
        case account = "screen_name"
        case profilePictureUrl = "profile_image_url_https"
    }
}
