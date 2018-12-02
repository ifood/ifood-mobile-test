//
//  RestApi.swift
//  ifood-devtest
//
//  Created by Rafael Zilião on 29/11/18.
//  Copyright © 2018 Rafael Zilião. All rights reserved.
//

import Foundation
import Moya

enum SentimentAnalysisApi {
    case text(text: String)
}

extension SentimentAnalysisApi: TargetType {
    var baseURL: URL {
        guard let url = URL(string: "https://language.googleapis.com/v1/documents:analyzeSentiment?key=\(NetworkManager.apiKey)") else {
            fatalError("baseURL could not be configured")
        }
        return url
    }
    
    var path: String {
        switch self {
        case .text:
            return ""
        }
    }
    
    var method: Moya.Method {
        switch self {
        case .text:
            return .post
        }
    }
    
    var sampleData: Data {
        return Data()
    }
    
    var parameters: [String: Any] {
        switch self {
        case .text(let text):
            return ["document": ["type": "PLAIN_TEXT", "content": text],
                    "encodingType": "UTF8"]
        }
    }
    
    var parameterEncoding: ParameterEncoding {
        switch self {
        case .text:
            return JSONEncoding.default
        }
    }
    
    var task: Task {
        switch self {
        case .text:
            return .requestParameters(parameters: parameters, encoding: parameterEncoding)
        }
    }
    
    var headers: [String : String]? {
        return ["Content-type": "application/json"]
    }
    
}
