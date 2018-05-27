//
//  Tweet.swift
//  Twimotion
//
//  Created by Antony on 26/05/18.
//  Copyright © 2018 Twimotion. All rights reserved.
//

import Foundation

struct Tweet: Decodable {
    let idStr: String
    let text: String
    let user: TwitterUser
}
