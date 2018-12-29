//
//  TwitterTargetType.swift
//  ifood-mobile-test
//
//  Created by Jean Vinge on 25/12/18.
//  Copyright © 2018 Jean Vinge. All rights reserved.
//

import Moya
import Utility

enum TwitterTargetType {
    case accessToken
    case latestTweets(username: String)
    case user(username: String)
}

extension TwitterTargetType: TargetType {

    var baseURL: URL { return URL(string: "https://api.twitter.com")! }

    var path: String {
        switch self {
        case .accessToken:
            return "/oauth2/token"
        case .latestTweets:
            return "/1.1/statuses/user_timeline.json"
        case .user:
            return "/1.1/users/show.json"
        }
    }

    var method: Moya.Method {
        switch self {
        case .accessToken:
            return .post
        case .latestTweets, .user:
            return .get
        }
    }

    var task: Task {
        switch self {
        case .accessToken:
            return .requestParameters(parameters: ["grant_type": "client_credentials"], encoding: URLEncoding.default)
        case .latestTweets(let username), .user(let username):
            return .requestParameters(parameters: ["screen_name": username], encoding: URLEncoding.default)
        }
    }

    var headers: [String: String]? {
        switch self {
        case .accessToken:
            return ["Authorization": "Basic \(TwitterKeys.encoded())"]
        default:
            return ["Authorization": "Bearer \(UserDefaults.getToken())"]
        }
    }
}
