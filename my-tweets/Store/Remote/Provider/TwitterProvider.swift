//
//  TwitterProvider.swift
//  my-tweets
//
//  Created by Gabriel Catice on 22/11/18.
//  Copyright © 2018 Gabriel Catice. All rights reserved.
//

import Moya

enum TwitterProvider {
    case searchUsers(term: String)
    case timeline(userId: String)
    case friends(userId: String)
}

extension TwitterProvider {
    var endpoint: String {
        return "\(baseUrl)\(path)"
    }
    
    var baseUrl: String {
        return "https://api.twitter.com"
    }
    
    var path: String {
        switch self {
        case .searchUsers:
            return "/1.1/users/search.json"
        case .timeline:
            return "/1.1/statuses/user_timeline.json"
        case .friends:
            return "/1.1/friends/list.json"
        }
    }
    
    var method: Moya.Method {
        switch self {
            case .friends, .searchUsers, .timeline: return .get
        }
    }
    
    var parameters: [String : Any] {
        switch self {
        case let .searchUsers(term):
            return ["q" : term]
        case let .timeline(userId):
            return ["user_id" : userId]
        case let .friends(userId):
            return ["user_id" : userId]
        }
    }
}

extension TwitterProvider: CachePolicyGettable {
    var cachePolicy: URLRequest.CachePolicy {
        return .reloadIgnoringLocalCacheData
    }
}
