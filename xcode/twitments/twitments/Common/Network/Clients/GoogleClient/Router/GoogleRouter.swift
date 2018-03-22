//
//  GoogleRouter.swift
//  twitments
//
//  Created by Ezequiel on 08/03/18.
//  Copyright © 2018 Ezequiel. All rights reserved.
//

import Foundation
import Alamofire

enum GoogleRouter: URLRequestConvertible {

    case sentimentOf(tweet: String)

    var method: HTTPMethod {
        switch self {
        case .sentimentOf:
            return .post
        }
    }

    var path: String {
        switch self {
        case .sentimentOf:
            return API.sentimentsPath
        }
    }

    func asURLRequest() throws -> URLRequest {

        let baseUrl = try API.googleBaseURL.appending(self.path).asURL()
        var urlRequest = URLRequest(url: baseUrl)
        urlRequest.httpMethod = method.rawValue

        switch self {
        case let .sentimentOf(message):
            let params = ["document": [
                "language": "en-US",
                "content": message,
                "type": "PLAIN_TEXT"]
            ]
            return try JSONEncoding.default.encode(urlRequest, with: params)
        }

    }

}
