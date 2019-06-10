//
//  SentimentalAnalyzerTargetType.swift
//  IfoodMobileTest
//
//  Created by André Vieira on 10/06/19.
//  Copyright © 2019 André Vieira. All rights reserved.
//

import Foundation

enum SentimentalAnalyzerTargetType {
    case getFeeling(String)
}

extension SentimentalAnalyzerTargetType: TargetType {
    var baseURL: URL {
        return URL(string: "https://language.googleapis.com")!
    }
    
    var path: String {
        switch self {
        case .getFeeling:
            return "/v1/documents:analyzeSentiment"
        }
    }
    
    var method: HTTPMethod {
        switch self {
        case .getFeeling:
            return .post
        }
    }
    
    var headers: [String: String]? {
        return nil
    }
    
    var task: Task {
        switch self {
        case .getFeeling(let tweet):
            return .requestCompositeParameters(bodyParameters: ["encodingType": "UTF8",
                                                                "document": [
                                                                    "type": "PLAIN_TEXT",
                                                                    "content": tweet]],
                                               bodyEncoding: JSONEncoding.default,
                                               urlParameters: ["key": "AIzaSyDFPUEvyJwc-CaFXtyRT4Vzyi6U_8G0KD8"])
        }
    }
}
