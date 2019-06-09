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
}

extension TwitterTargetType: TargetType {
    var baseURL: URL {
        return URL(string: "https://api.twitter.com")!
    }
    
    var path: String {
        return "oauth2/token"
    }
    
    var method: HTTPMethod {
        return .post
    }
    
    var headers: [String: String]? {
        return ["Authorization": "Basic " + ("h43koqUZDVh32XLsyP9JmH4Aw:SWhkGqn9BNWINWf6TjeebUx0DmTvZuAKMcomxnCn0MjjllE78J".data(using: .utf8)?.base64EncodedString() ?? "")]
    }
    
    var parameters: [String: Any]? {
        return  ["grant_type": "client_credentials"]
    }
    
    var parameterEncoding: ParameterEncoding {
        return URLEncoding.default
    }
}
