//
//  User.swift
//  my-tweets
//
//  Created by Gabriel Catice on 22/11/18.
//  Copyright © 2018 Gabriel Catice. All rights reserved.
//

import Foundation

public struct User {
    public let id: String
    public let name: String?
    public let screenName: String?
    public let profileImageUrl: String?
    public let profileBackgroundImageUrl: String?
    public let description: String?
    
    public init(id: String, name: String?, screenName: String?, profileImageUrl: String?, profileBackgroundImageUrl: String?, description: String?) {
        self.id = id
        self.name = name
        self.screenName = screenName
        self.profileImageUrl = profileImageUrl
        self.profileBackgroundImageUrl = profileBackgroundImageUrl
        self.description = description
    }
}

