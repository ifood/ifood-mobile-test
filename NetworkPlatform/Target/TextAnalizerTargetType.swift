//
//  GoogleTargetType.swift
//  ifood-mobile-test
//
//  Created by Jean Vinge on 25/12/18.
//  Copyright © 2018 Jean Vinge. All rights reserved.
//

import Moya
import Utility

enum TextAnalizerTargetType {
    case analize(text: String)
}

extension TextAnalizerTargetType: TargetType {

    var baseURL: URL { return URL(string: "https://language.googleapis.com/v1")! }

    var path: String {
        switch self {
        case .analize:
            return "/documents:analyzeSentiment"
        }
    }

    var method: Moya.Method {
        switch self {
        case .analize:
            return .post
        }
    }

    var task: Task {
        switch self {
        case .analize(let text):
            let params = ["document": ["content": text, "type": "PLAIN_TEXT"], "encodingType": "UTF8"] as [String: Any]
            return .requestCompositeParameters(bodyParameters: params, bodyEncoding: JSONEncoding.default, urlParameters: ["key": GoogleAPIKey.apiKey])
        }
    }
}
