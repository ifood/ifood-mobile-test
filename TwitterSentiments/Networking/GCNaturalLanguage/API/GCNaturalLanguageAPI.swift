//
//  GCNaturalLanguageAPI.swift
//  TwitterSentiments
//
//  Created by Marina Azevedo on 14/07/19.
//  Copyright © 2019 Marina Azevedo. All rights reserved.
//

import Foundation

enum NaturalLangAPI {
    case analyzeSentiment(text: String)
    
    private var apiKey: String { return "59904771337e946af51522fa0f903f2b61eef501" }
}

extension NaturalLangAPI: TargetType {
    var baseURL: URL { return URL(string: "https://language.googleapis.com/v1")! }
    
    var path: String {
        switch self {
        case .analyzeSentiment:
            return "/documents:analyzeSentiment?key=\(apiKey)"
        }
    }
    var method: HttpMethod {
        switch self {
        case .analyzeSentiment:
            return .post
        }
    }
    
    var body: Data? {
        return try? JSONSerialization.data(withJSONObject: self.parameters, options: [])
    }
    
    var parameters: [String: Any] {
        switch self {
        case .analyzeSentiment(let text):
            return [
                "document": [ "content": text, "type": "PLAIN_TEXT" ],
                "encodingType": "UTF8"
            ]
        }
    }
    
    var headers: [String: String]? {
        return ["Content-type": "application/json"]
    }
    
}
