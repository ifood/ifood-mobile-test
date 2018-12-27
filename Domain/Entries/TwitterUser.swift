//
//  TwitterUser.swift
//  ifood-mobile-test
//
//  Created by Jean Vinge on 26/12/18.
//  Copyright © 2018 Jean Vinge. All rights reserved.
//

import Foundation

public struct TwitterUser: Codable {
    
    enum CodingKeys: String, CodingKey {
        case profileImageURL = "profile_image_url"
        case id = "id_str"
        case name
        case screenName = "screen_name"
    }
    
    public let id: String
    public let name: String
    public let screenName: String
    public let profileImageURL: URL
    
    public var decoratedUserName: String {
        return "@\(self.screenName)"
    }
}
