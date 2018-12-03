//
//  SentimentAPI.swift
//  SentimentAnalysis
//
//  Created by Thales Frigo on 01/12/18.
//  Copyright © 2018 Thales Frigo. All rights reserved.
//

import Foundation
import Moya

enum SentimentAPI {
    case analyzeDocument(content: String)
}

extension SentimentAPI: TargetType {
    
    var baseURL: URL {
        return SentimentAPIConfig.baseURL
    }
    
    var path: String {
        return "documents:analyzeSentiment"
    }
    
    var method: Moya.Method {
        return .post
    }
    
    var parameters: [String: Any]? {
        switch self {
        case .analyzeDocument(let content):
            let document: [String: Any] = [
                "document": [
                    "content": content,
                    "type": "PLAIN_TEXT"
                ],
                "encodingType": "UTF8"
            ]
            
            return document
        }
    }
    
    var task: Task {
        return .requestParameters(parameters: parameters!, encoding: JSONEncoding.default)
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
