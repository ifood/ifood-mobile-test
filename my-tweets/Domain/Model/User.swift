//
//  User.swift
//  my-tweets
//
//  Created by Gabriel Catice on 22/11/18.
//  Copyright © 2018 Gabriel Catice. All rights reserved.
//

import Foundation

public struct User {
    public let name: String?
    public let screenName: String?
    public let profileImageUrl: String?
    
    public init(name: String?, screenName: String?, profileImageUrl: String?) {
        self.name = name
        self.screenName = screenName
        self.profileImageUrl = profileImageUrl
    }
}
