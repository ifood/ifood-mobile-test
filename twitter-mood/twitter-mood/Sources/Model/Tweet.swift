//
//  Tweet.swift
//  twitter-mood
//
//  Created by Dennis Merli on 7/6/19.
//  Copyright © 2019 Dennis Merli. All rights reserved.
//

import Foundation

class Tweet: Codable {
    var createdAt: String?
    var id: Double?
    var idStr: String?
    var text: String?
    var user: User?
    
    enum CodingKeys: String, CodingKey {
        case createdAt = "created_at"
        case id = "id"
        case idStr = "id_str"
        case text = "text"
        case user = "user"
    }
}

// MARK: - User
struct User: Codable {
    var id: Int?
    var idStr: String?
    var name: String?
    var screenName: String?
    var location: String?
    var userDescription: String?
    var url: String?
    var followersCount: Int?
    var friendsCount: Int?
    var createdAt: String?
    var profileImageURLHTTPS: String?
    
    enum CodingKeys: String, CodingKey {
        case id
        case idStr = "id_str"
        case name
        case screenName = "screen_name"
        case location
        case userDescription = "description"
        case url
        case followersCount = "followers_count"
        case friendsCount = "friends_count"
        case createdAt = "created_at"
        case profileImageURLHTTPS = "profile_image_url_https"
    }
}

