//
//  User.swift
//  SentimentAnalysis
//
//  Created by Thales Frigo on 28/11/18.
//  Copyright © 2018 Thales Frigo. All rights reserved.
//

import Foundation

struct User: Codable {
    
    let name: String
    let screenName: String
    let image: URL
    
    init(name: String, screenName: String, image: URL) {
        self.name = name
        self.screenName = screenName
        self.image = image
    }
    
    enum CodingKeys: String, CodingKey {
        case name
        case screenName = "screen_name"
        case image = "profile_image_url_https"
    }
}
