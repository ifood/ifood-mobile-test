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
    func setAcessToken(url: inout URLRequest)
}

extension AuthProtocol {
    func setAcessToken(url: inout URLRequest) {
    }
}

enum TwitterRouter: URLRequestConvertible, AuthProtocol {
    
    // MARK: Services
    case authentication(headers: [String:Any], grantType: [String:Any])
    case userTimeline(screenName:String)
    
    var method: Alamofire.HTTPMethod {
        switch self {
        case .userTimeline:
            return .get
        case .authentication(let headers, let grantTypeBody):
            return .post
        }
    }
    
    var path: String {
        switch self {
        case .userTimeline:
            return API.userTimelinePath
        case .authentication(let headers, let grantTypeBody):
            return API.twitterBaseURL
        }
    }
    
    func asURLRequest() throws -> URLRequest {
       
        switch self {
        case let .userTimeline(screenName):
          
            let parameters = ["screen_name":screenName]
            var components = URLComponents()
            components.scheme = API.twitterScheme
            components.host   = API.twitterBaseURL
            components.path   = path
            
            if !parameters.isEmpty {
                components.queryItems = [URLQueryItem]()
                for (key, value) in parameters {
                    let queryItem = URLQueryItem(name: key, value: "\(value)")
                    components.queryItems!.append(queryItem)
                }
            }
            
            var urlRequest = URLRequest(url: components.url!)
            urlRequest.httpMethod = method.rawValue
            return try Alamofire.JSONEncoding.default.encode(urlRequest)

        case .authentication(let headers, let grantType):
            
            let parameters = ["screen_name":headers]
            var components = URLComponents()
            components.scheme = API.twitterScheme
            components.host   = API.twitterBaseURL
            components.path   = path
            
            if !parameters.isEmpty {
                components.queryItems = [URLQueryItem]()
                for (key, value) in parameters {
                    let queryItem = URLQueryItem(name: key, value: "\(value)")
                    components.queryItems!.append(queryItem)
                }
            }
            
            var urlRequest = URLRequest(url: components.url!)
            urlRequest.httpMethod = method.rawValue
            return try Alamofire.JSONEncoding.default.encode(urlRequest)
        }
    }
}
