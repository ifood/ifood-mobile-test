//
//  User.swift
//  TwitterSentiments
//
//  Created by Marina Azevedo on 14/07/19.
//  Copyright © 2019 Marina Azevedo. All rights reserved.
//

import Foundation

struct User: Decodable {
    let idStr: String
    let name: String
    let screenName: String
    let profileImageUrlHttps: String
}
