//
//  TwitterAPI.swift
//  iFoodTest
//
//  Created by Mauricio Cesar Maniglia Junior on 07/06/18.
//  Copyright © 2018 Mauricio Cesar Maniglia Junior. All rights reserved.
//

import Foundation

struct TwitterAPI {
    static let baseURLString = "https://api.twitter.com"
    static let clientKey = "tkNJD1HF5xfXTtKWbjmAiy5Bw"
    static let clientSecret = "n2Gv89UiSpvE4oexVFtuByTCJMVJ00vjVqUBBAlRRWqZ3rivpj"    
}

enum TwitterAPIEndPoint {
    case getAccessToken
    case getUser(String)
    case getLastestTweets(String)
    
    var urlRequest: URLRequest {
        var request = URLRequest(url: url!)
        request.httpMethod = method
        request.httpBody = body
        _ = headers.map { (key, value) in
            request.addValue(value, forHTTPHeaderField: key)
        }
        
        return request
    }
    
    private var url: URL? {
        switch self {
        case .getAccessToken:
            return URL(string: "\(TwitterAPI.baseURLString)/oauth2/token")
        case .getUser(let username):
            return URL(string: "\(TwitterAPI.baseURLString)/1.1/users/show.json?screen_name=\(username)")
        case .getLastestTweets(let username):
            return URL(string: "\(TwitterAPI.baseURLString)/1.1/statuses/user_timeline.json?screen_name=\(username)")
        }
    }
    
    private var method: String {
        switch self {
        case .getAccessToken:
            return "POST"
        case .getUser, .getLastestTweets:
            return "GET"
        }
    }
    
    private var body: Data? {
        switch self {
        case .getAccessToken:
            return "grant_type=client_credentials".data(using: String.Encoding.utf8)
        default:
            return nil
        }
    }
    
    private var headers: [String: String] {
        switch self {
        case .getAccessToken:
            let basicAuthToken = "\(TwitterAPI.clientKey):\(TwitterAPI.clientSecret)".data(using: .utf8)
            let encoded = basicAuthToken?.base64EncodedString() ?? ""
            
            return [
                "Content-Type": "application/x-www-form-urlencoded;charset=UTF-8",
                "Authorization": "Basic \(encoded)"
            ]
        default:
            return [
                "Content-type": "application/json",
                "Authorization": "Bearer \(TwitterAccessToken.accessToken())"
            ]
        }
    }
}
