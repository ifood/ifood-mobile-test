//
//  TwitterAPI.swift
//  TwitterSentiments
//
//  Created by Marina Azevedo on 14/07/19.
//  Copyright © 2019 Marina Azevedo. All rights reserved.
//

import Foundation

enum TwitterAPI {
    case getAccessToken
    case getLastestTweets(username: String)
    case getUser(username: String)
    
    private var clientKey: String { return "7rqlL6u9N0xREBazpDXFxWx5d" }
    private var clientSecret: String { return "4hmxAIkk4OjAx6C6lbx75dP7KWWeUQ5AGB5V5i1ITNJnn84hyY" }
}

extension TwitterAPI: TargetType {
    var baseURL: URL { return URL(string: "https://api.twitter.com")! }
    
    var path: String {
        switch self {
        case .getAccessToken:
            return "/oauth2/token"
        case .getUser(let username):
            return "/1.1/users/show.json?screen_name=\(username)"
        case .getLastestTweets(let username):
            return "/1.1/statuses/user_timeline.json?screen_name=\(username)"
        }
    }
    var method: HttpMethod {
        switch self {
        case .getAccessToken:
            return .post
        case .getUser, .getLastestTweets:
            return .get
        }
    }
    
    var body: Data? {
        switch self {
        case .getAccessToken:
            return "grant_type=client_credentials".data(using: String.Encoding.utf8)
        default:
            return nil
        }
        
    }
    
    var headers: [String: String]? {
        switch self {
        case .getAccessToken:
            let basicAuthToken = "\(clientKey):\(clientSecret)".data(using: .utf8)
            let encoded = basicAuthToken?.base64EncodedString() ?? ""
            return [
                "Content-Type": "application/x-www-form-urlencoded;charset=UTF-8",
                "Authorization": "Basic \(encoded)"
            ]
        default:
            return [
                "Content-type": "application/json",
                "Authorization": "Bearer \("1149125317568749568-jFH4thOZqkdGkVGeqm5LeR4Gzf15US"))"
            ]
        }
    }
    
}

