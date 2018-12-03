//
//  TwitterAPI.swift
//  SentimentAnalysis
//
//  Created by Thales Frigo on 27/11/18.
//  Copyright © 2018 Thales Frigo. All rights reserved.
//

import Foundation
import Moya

enum TwitterAPI {
    case auth
    case search(username: String, maxId: Int64?)
}

extension TwitterAPI: TargetType {
    
    var baseURL: URL {
        return TwitterAPIConfig.baseURL
    }
    
    var path: String {
        switch self {
        case .auth:
            return "oauth2/token"
        case .search:
            return "1.1/statuses/user_timeline.json"
        }
    }
    
    var method: Moya.Method {
        switch self {
        case .auth:
            return .post
        case .search:
            return .get
        }
    }
    
    var parameters: [String: Any]? {
        switch self {
        case .auth:
            return ["grant_type": "client_credentials"]
        case .search(let username, let maxId):
            var parameters: [String: Any] = ["screen_name": username, "count": 10]
            if let maxId = maxId {
                parameters["max_id"] = maxId
            }
            return parameters
        }
    }
    
    var task: Task {
        return .requestParameters(parameters: parameters!, encoding: URLEncoding.default)
    }
    
    var headers: [String : String]? {
        return nil
    }
    
    var sampleData: Data {
        return Data()
    }
    
    var validationType: ValidationType {
        return .successCodes
    }
}

extension TwitterAPI: AccessTokenAuthorizable {
    
    var authorizationType: AuthorizationType {
        switch self {
        case .auth:
            return .basic
        case .search:
            return .bearer
        }
    }
}
