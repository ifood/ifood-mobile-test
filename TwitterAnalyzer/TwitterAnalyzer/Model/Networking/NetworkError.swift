//
//  NetworkError.swift
//  TwitterAnalyzer
//
//  Created by Bruno Vieira on 19/06/19.
//  Copyright © 2019 Bruno Vieira. All rights reserved.
//

import Foundation

struct NetworkError: ErrorProtocol {
    let code: Code
    var description: String {
        get {
            return mapMessage()
        }
    }    
    
    init(_ code: NetworkError.Code) {
        self.code = code
    }
    
    enum Code {
        case unknown
        case unexpected
        case urlNotFound
        case invalidURL
        case invalidRequest
        case invalidRequestBody
        case invalidResponse
        case jsonParse
        case serializationError
        case connectivity
    }
    
    private func mapMessage() -> String {
        switch code {
        case .unknown:
            return "An unknown error has occured. Try again later."
        case .unexpected:
            return "An unexpected error has occured. Check your internet connection and try again."
        case .urlNotFound:
            return "URL not found."
        case .invalidURL:
            return "Invalid URL."
        case .invalidRequest:
            return "Invalid request"
        case .invalidRequestBody:
            return "Invalid request body."
        case .invalidResponse:
            return "Invalid response"
        case .jsonParse:
            return "JSON parsing error."
        case .serializationError:
            return "Object serialization error."
        case .connectivity:
            return "Network connectivity error."
        }
    }
}
