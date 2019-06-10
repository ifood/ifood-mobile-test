//
//  SentimentalAnalyzerTargetType.swift
//  IfoodMobileTest
//
//  Created by André Vieira on 10/06/19.
//  Copyright © 2019 André Vieira. All rights reserved.
//

import Foundation

enum SentimentalAnalyzerTargetType {
    case oauth
    case findTweets(String)
}

extension SentimentalAnalyzerTargetType: TargetType {
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
    
    var parameters: [String: Any]? {
        switch self {
        case .oauth:
            return  ["grant_type": "client_credentials"]
        case .findTweets(let username):
            return ["screen_name": username]
        }
    }
    
    var parameterEncoding: ParameterEncoding {
        return URLEncoding.default
    }
}
