//
//  Tweet.swift
//  MoodAnalyzer
//
//  Created by Carlos Henrique Salvador on 16/12/18.
//  Copyright © 2018 Carlos Henrique Salvador. All rights reserved.
//

import Foundation

struct Tweet : Decodable {
    
    var date : String
    var text : String
    var user : User
    var mood : Mood? = nil
    
    enum CodingKeys: String, CodingKey {
        case date = "created_at"
        case text
        case user
    }
}

struct User : Decodable {
    
    var name : String
    var bio : String
    var profileImageURL : String
    
    enum CodingKeys: String, CodingKey {
        case name
        case bio = "description"
        case profileImageURL = "profile_image_url_https"
    }
    
    
}
