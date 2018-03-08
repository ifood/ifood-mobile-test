//
//  API.swift
//  twitments
//
//  Created by Ezequiel on 08/03/18.
//  Copyright © 2018 Ezequiel. All rights reserved.
//

import Foundation
struct API {    
    // MARK: Twitter
    static let twitterScheme = "https"
    static let twitterBaseURL = Configurations.shared.twitterBaseURL()
    static let userTimelinePath = "/"
}

