//
//  TwitterTargetType.swift
//  ifood-mobile-test
//
//  Created by Jean Vinge on 25/12/18.
//  Copyright © 2018 Jean Vinge. All rights reserved.
//

import Moya

enum TwitterTargetType {
    case accessToken
    case latestTweets(username: String)
    case user(username: String)

    private var clientKey: String { return "mrbecy5yseOOyu2yjp6SIb4po" }
    private var clientSecret: String { return "tU2y3LdBXOnSShuwOPedrLzAVkpr5b7DTNarZSggOeGRX6Ncjb" }
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
            let encoded = "\(clientKey):\(clientSecret)".data(using: .utf8)?.base64EncodedString() ?? ""
            return ["Authorization": "Basic \(encoded)"]
        default:
            return ["Authorization": "Bearer \(UserDefaults.getToken())"]
        }
    }
}
