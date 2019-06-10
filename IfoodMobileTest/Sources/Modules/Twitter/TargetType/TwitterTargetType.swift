//
//  TwitterTargetType.swift
//  IfoodMobileTest
//
//  Created by André Vieira on 09/06/19.
//  Copyright © 2019 André Vieira. All rights reserved.
//

import Foundation

enum TwitterTargetType {
    case oauth
    case findTweets(String)
}

extension TwitterTargetType: TargetType {
    var baseURL: URL {
        return URL(string: "https://api.twitter.com")!
    }
    
    var path: String {
        switch self {
        case .oauth:
            return "oauth2/token"
        case .findTweets:
            return "/1.1/statuses/user_timeline.json"
        }
    }
    
    var method: HTTPMethod {
        switch self {
        case .oauth:
            return .post
        case .findTweets:
            return .get
        }
    }
    
    var headers: [String: String]? {
        switch self {
        case .oauth:
            return ["Authorization": "Basic " + ("h43koqUZDVh32XLsyP9JmH4Aw:SWhkGqn9BNWINWf6TjeebUx0DmTvZuAKMcomxnCn0MjjllE78J".data(using: .utf8)?.base64EncodedString() ?? "")]
        case .findTweets:
            return ["Authorization": "Bearer \(UserDefaults.getToken())"]
        }
    }
    
    var task: Task {
        switch self {
        case .oauth:
            return .requestParameters(parameters: ["grant_type": "client_credentials"],
                                      encoding: URLEncoding.default)
        case .findTweets(let screenName):
            return .requestParameters(parameters: ["screen_name": screenName],
                                      encoding: URLEncoding.default)
        }
    }
}
