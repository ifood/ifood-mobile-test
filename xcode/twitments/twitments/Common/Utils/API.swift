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
    static let twitterBaseURL = Configurations.shared.twitterBaseURL()
    static let userTimelinePath = "/1.1/statuses/user_timeline.json"
    static let authenticationPath = "/oauth2/token"
}

