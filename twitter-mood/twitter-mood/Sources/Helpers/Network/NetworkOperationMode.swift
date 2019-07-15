//
//  NetworkOperationMode.swift
//  twitter-mood
//
//  Created by Dennis Merli on 7/6/19.
//  Copyright © 2019 Dennis Merli. All rights reserved.
//

import Foundation

enum NetworkOperationMode {
    case normal
    case stubResponse
    case stubWithDelay(delay: TimeInterval)
    case stubWithError(statusCode: Int?, data: Data?)
    
    static func fromString(type: String) -> NetworkOperationMode {
        switch type {
        case "stub":
            return .stubResponse
        case "normal":
            return .normal
        case "stubDelay":
            return .stubWithDelay(delay: 0.5)
        case "stubWithError":
            return .stubWithError(statusCode: 500, data: nil)
        default:
            return .normal
        }
    }
    
    func toString() -> String {
        switch self {
        case .stubResponse:
            return "stub"
        case .normal:
            return "normal"
        case .stubWithDelay:
            return "stubDelay"
        case .stubWithError:
            return "stubWithError"
        }
    }
}

extension NetworkOperationMode: Equatable {
    static func == (lhs: NetworkOperationMode, rhs: NetworkOperationMode) -> Bool {
        switch (lhs, rhs) {
        case (let .stubWithDelay(delay1), let .stubWithDelay(delay2)):
            return delay1 == delay2
        case (let .stubWithError(code1, data1), let .stubWithError(code2, data2)):
            return code1 == code2 && data1 == data2
        case (.stubResponse, .stubResponse), (.normal, .normal):
            return true
        default:
            return false
        }
    }
}

