//
//  TwitterProvider.swift
//  twitter-mood
//
//  Created by Dennis Merli on 7/6/19.
//  Copyright © 2019 Dennis Merli. All rights reserved.
//

import Moya

enum TwitterProvider {
    case fetchTweets(username: String, count: Int)
    case authorize
}

extension TwitterProvider: TargetType {
    var baseURL: URL {
        switch self {
        case .fetchTweets(username: _, count: _):
            return NetworkManager.shared.currentEnvVars?.apiVariablesTwitter?.baseURL! ?? URL(string: "")!
        case .authorize:
            return NetworkManager.shared.currentEnvVars?.apiVariablesTwitter?.baseURLWithoutVersion! ?? URL(string: "")!
        }
    }
    
    var path: String {
        switch self {
        case .fetchTweets(username: _, count: _):
            return "/statuses/user_timeline.json"
        case .authorize:
            return "oauth2/token"
        }
    }
    
    var method: Moya.Method {
        switch self {
        case .fetchTweets(username: _, count: _):
            return .get
        case .authorize:
            return .post
        }
    }
    
    var sampleData: Data {
        switch self {
        case .fetchTweets(username: _, count: _):
            return TweetMock.loadTweetMock()
        case .authorize:
            return Data()
        }
    }
    
    var task: Task {
        switch self {
        case .fetchTweets(let username, let count):
            let parameters = ["screen_name": username, "count": count] as [String: Any]
            return .requestParameters(parameters: parameters, encoding: URLEncoding.default)
        case .authorize:
            let parameters = ["grant_type": "client_credentials"]
            return .requestParameters(parameters: parameters, encoding: URLEncoding.default)
        }
    }
    
    var headers: [String: String]? {
        switch self {
        case .fetchTweets(username: _, count: _):
            return ["Content-type": "application/json", "Authorization": "Bearer \(NetworkManager.shared.twitterAuthToken() ?? "")"]
        case .authorize:
            return ["Content-type": "application/x-www-form-urlencoded;charset=UTF-8", "Authorization": "Basic \(NetworkManager.shared.currentEnvVars?.apiVariablesTwitter?.apiKey ?? "")"]
        }
    }
}

extension TwitterProvider: AccessTokenAuthorizable {
    var authorizationType: AuthorizationType {
        switch self {
        case .fetchTweets(username: _, count: _):
            return .bearer
        case .authorize:
            return .basic
        }
    }
}

