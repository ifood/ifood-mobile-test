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
    case userTimeline(screenName:String)
    
    var method: Alamofire.HTTPMethod {
        switch self {
        case .userTimeline:
            return .get
        }
    }
    
    var path: String {
        switch self {
        case .userTimeline:
            return API.userTimelinePath
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

        }
    }
}
