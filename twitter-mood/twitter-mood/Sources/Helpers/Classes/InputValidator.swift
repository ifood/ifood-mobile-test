//
//  InputValidator.swift
//  twitter-mood
//
//  Created by Dennis Merli on 7/9/19.
//  Copyright © 2019 Dennis Merli. All rights reserved.
//

import Foundation

class InputValidator {
    
    /// Validate name
    class func validateName(_ name: String, minCharacters: Int = 2) -> ValidationResult {
        if name.isEmpty {
            return .empty
        }
        
        if name.count < minCharacters {
            return .fail(message: BaseLocalizable.error)
        }
        
        return .success
    }
}

