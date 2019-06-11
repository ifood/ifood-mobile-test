//
//  TwitterUserModel.swift
//  IfoodMobileTest
//
//  Created by André Vieira on 09/06/19.
//  Copyright © 2019 André Vieira. All rights reserved.
//

import Foundation

public struct TwitterUser: Codable {
    
    var id: String?
    var name: String?
    var screenName: String?
    var profileImageURL: URL?
    
    enum CodingKeys: String, CodingKey {
        case profileImageURL = "profile_image_url_https"
        case id = "id_str"
        case name
        case screenName = "screen_name"
    }
}
