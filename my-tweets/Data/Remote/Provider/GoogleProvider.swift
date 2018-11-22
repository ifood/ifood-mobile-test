//
//  GoogleProvider.swift
//  my-tweets
//
//  Created by Gabriel Catice on 22/11/18.
//  Copyright © 2018 Gabriel Catice. All rights reserved.
//

import Moya

public enum GoogleProvider {
    case sentiment(text: String)
}

extension GoogleProvider: TargetType {
    
    public var baseURL: URL {
        switch self {
        case .sentiment:
            return URL(string: "https://language.googleapis.com/v1")!
        }
    }
    
    public var path: String {
        switch self {
        case .sentiment:
            return "/documents:analyzeSentiment"
        }
    }
    
    public var method: Moya.Method {
        switch self {
        case .sentiment:
            return .post
        }
    }
    
    public var sampleData: Data { return "".utf8Encoded }
    
    public var task: Task {
        switch self {
        case let .sentiment(text):
            return .requestCompositeParameters(
                bodyParameters: [ "document": [ "type": "PLAIN_TEXT", "content": "\(text)"], "encodingType": "UTF8" ],
                bodyEncoding: JSONEncoding.default,
                urlParameters: ["key": "AIzaSyCMSoodvaZrcKCms0U4r61egJUcG2a1sgs"])
        }
    }
    
    public var headers: [String : String]? {
        switch self {
        case .sentiment:
            return ["Content-type": "application/json"]
        }
    }
}

fileprivate extension String {
    
    var urlEscaped: String {
        return addingPercentEncoding(withAllowedCharacters: .urlHostAllowed)!
    }
    
    var utf8Encoded: Data {
        return data(using: .utf8)!
    }
}
