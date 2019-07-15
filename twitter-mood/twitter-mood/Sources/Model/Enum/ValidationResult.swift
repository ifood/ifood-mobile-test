//
//  ValidationResult.swift
//  twitter-mood
//
//  Created by Dennis Merli on 7/9/19.
//  Copyright © 2019 Dennis Merli. All rights reserved.
//

import Foundation

enum ValidationResult {
    case success
    case empty
    case fail(message: String)
}

extension ValidationResult {
    var isValid: Bool {
        switch self {
        case .success:
            return true
        default:
            return false
        }
    }
}

extension ValidationResult: Equatable {
    static func == (lhs: ValidationResult, rhs: ValidationResult) -> Bool {
        switch (lhs, rhs) {
        case (.success, .success):
            return true
        case (.empty, .empty):
            return true
        case ( .fail(let message1), .fail(let message2)):
            return message1 == message2
        default:
            return false
        }
    }
}
