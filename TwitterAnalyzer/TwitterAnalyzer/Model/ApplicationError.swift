//
//  Error.swift
//  TwitterAnalyzer
//
//  Created by Bruno Vieira on 21/06/19.
//  Copyright © 2019 Bruno Vieira. All rights reserved.
//

import Foundation

struct ApplicationError: ErrorProtocol {
    var description: String {
        get {
            return mapMessage()
        }
    }
    let code: Code
    
    init(_ code: ApplicationError.Code) {
        self.code = code
    }
    
    private func mapMessage() -> String {
        switch code {
        case .userNotFound:
            return "User account not found."
        case .userInfoUnavailable:
            return "Unable to get user information."
        case .textAnalysisUnavailable:
            return "Unable to get text analyzed."
        case .connection:
            return "Unable to connect to the internet. Please, make sure you have a valid internet connection and try again."
        case .unknown:
            return "An unknown error occurred. Please, try again later."
        }
    }
    
    enum Code {
        case userNotFound
        case userInfoUnavailable
        case textAnalysisUnavailable
        case connection
        case unknown
    }    
}
