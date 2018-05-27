//
//  TwitterAPI.swift
//  Twimotion
//
//  Created by Antony on 26/05/18.
//  Copyright © 2018 Twimotion. All rights reserved.
//

import Foundation

enum TwitterAPI {
    case getAccessToken()
    case getLastestTweets(username: String)
    case getUser(username: String)

    private var clientKey: String { return "ssFq5LOzXr51UDCv8W9eSlHWz" }
    private var clientSecret: String { return "ePa5QoSQScJdqnh3ocWJxAfQ2kFekAZrdaWrb2gHPs6Rn2b5m3" }
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
                "Authorization": "Bearer \(TwitterAccessToken.loadAccessToken())"
            ]
        }
    }

}

