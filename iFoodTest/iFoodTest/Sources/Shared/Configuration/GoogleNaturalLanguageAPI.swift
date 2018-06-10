//
//  GoogleNaturalLanguageAPI.swift
//  iFoodTest
//
//  Created by Mauricio Cesar Maniglia Junior on 09/06/18.
//  Copyright © 2018 Mauricio Cesar Maniglia Junior. All rights reserved.
//

import Foundation

struct GoogleNaturalLanguageAPI {
    static let baseURLString = "https://language.googleapis.com/v1"
    static let apiKey = "AIzaSyCl85A3X1tIKS2mq9UDyrEXNbn2eMQHTnU"
}

enum GoogleNaturalLanguageAPIEndPoint {
    case analyzeSentiment(text: String)
    
    var urlRequest: URLRequest {
        var request = URLRequest(url: url!)
        request.httpMethod = method
        request.httpBody = body
        _ = headers.map { (key, value) in
            request.addValue(value, forHTTPHeaderField: key)
        }
        
        return request
    }
    
    private var url: URL? {
        switch self {
        case .analyzeSentiment:
            return URL(string: "\(GoogleNaturalLanguageAPI.baseURLString)/documents:analyzeSentiment?key=\(GoogleNaturalLanguageAPI.apiKey)")
        }
    }
    
    private var method: String {
        switch self {
        case .analyzeSentiment:
            return "POST"
        }
    }
    
    private var body: Data? {
        switch self {
        case .analyzeSentiment(let text):
            let parameters: [String: Any] = [
                "document": [ "content": text, "type": "PLAIN_TEXT" ],
                "encodingType": "UTF8"
            ]
            return try? JSONSerialization.data(withJSONObject: parameters, options: [])
        }
    }
    
    private var headers: [String: String] {
        switch self {
        case .analyzeSentiment:
            return ["Content-type": "application/json"]
        }
    }
}
