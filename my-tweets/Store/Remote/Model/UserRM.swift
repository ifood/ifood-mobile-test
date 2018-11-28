//
//  UserRM.swift
//  my-tweets
//
//  Created by Gabriel Catice on 28/11/18.
//  Copyright © 2018 Gabriel Catice. All rights reserved.
//

import Foundation

struct UserRM: Codable {
    public let name: String
    public let screenName: String
    public let profileImage: String
    
    enum CodingKeys: String, CodingKey {
        case name = "name"
        case screenName = "screen_name"
        case profileImage = "profile_image_url_https"
    }
}

extension UserRM {
    func toDomainModel() -> User {
        return User(name: name, screenName: screenName, profileImageUrl: profileImage)
    }
}
