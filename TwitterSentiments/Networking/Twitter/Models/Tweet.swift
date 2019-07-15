//
//  Tweet.swift
//  TwitterSentiments
//
//  Created by Marina Azevedo on 14/07/19.
//  Copyright © 2019 Marina Azevedo. All rights reserved.
//

import Foundation

struct Tweet: Decodable {
    let idStr: String
    let text: String
    let user: User
    let createdAt: Date
}
