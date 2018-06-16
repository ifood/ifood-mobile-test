//
//  TwitterUser.swift
//  Twimotion
//
//  Created by Antony on 27/05/18.
//  Copyright © 2018 Twimotion. All rights reserved.
//

import Foundation

struct TwitterUser: Decodable {
    let idStr: String
    let name: String
    let screenName: String
    let profileImageUrlHttps: String
}
