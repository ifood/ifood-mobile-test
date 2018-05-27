//
//  NaturalLangAPI.swift
//  Twimotion
//
//  Created by Antony on 27/05/18.
//  Copyright © 2018 Twimotion. All rights reserved.
//

import Foundation

enum NaturalLangAPI {
    case analyzeSentiment(text: String)

    private var apiKey: String { return "AIzaSyB6LnPVbQutH5Lk40JNilxHfvxbafClT0U" }
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
