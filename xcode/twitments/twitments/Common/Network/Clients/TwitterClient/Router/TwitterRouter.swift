//
//  TwitterRouter.swift
//  twitments
//
//  Created by Ezequiel on 08/03/18.
//  Copyright © 2018 Ezequiel. All rights reserved.
//

import Alamofire
import Foundation

protocol AuthProtocol {
    func setAcessToken(urlRequest: inout URLRequest)
}

extension AuthProtocol {
    func setAcessToken(urlRequest: inout URLRequest) {
        let token = AccessTokenDAO.getToken()
        urlRequest.setValue("Bearer " + token, forHTTPHeaderField: "Authorization")
    }
}

enum TwitterRouter: URLRequestConvertible, AuthProtocol {
    
    // MARK: Services
    case authentication(headers: [String: String], grantType: Data)
    case userTimeline(screenName:String)
    
    var method: Alamofire.HTTPMethod {
        switch self {
        case .userTimeline:
            return .get
        case .authentication:
            return .post
        }
    }
    
    var path: String {
        switch self {
        case .userTimeline:
            return API.userTimelinePath
        case .authentication:
            return API.authenticationPath
        }
    }
    
    func asURLRequest() throws -> URLRequest {
       
        let baseUrl = try API.twitterBaseURL.asURL()
        var urlRequest = URLRequest(url: baseUrl.appendingPathComponent(path))
        urlRequest.httpMethod = method.rawValue
        
        switch self {
        case let .userTimeline(screenName):
            
            let params: Parameters = ["screen_name": screenName, "count": 200]
            setAcessToken(urlRequest: &urlRequest)
            return try URLEncoding.default.encode(urlRequest, with: params)
            
        case .authentication(let headers, let grantType):
            urlRequest.allHTTPHeaderFields = headers
            urlRequest.httpBody = grantType
            return try Alamofire.JSONEncoding.default.encode(urlRequest)
        }
    }
}
