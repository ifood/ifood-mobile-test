//
//  ProjectUtils.swift
//  twitter-mood
//
//  Created by Dennis Merli on 7/6/19.
//  Copyright © 2019 Dennis Merli. All rights reserved.
//

import Foundation

final class ProjectUtils {
    
    /// Check if is running Unit tests
    class func isRunningUnitTests() -> Bool {
        if ProcessInfo.processInfo.environment["XCTestConfigurationFilePath"] != nil {
            return true
        } else {
            return false
        }
    }
    
    /// Check if is running UI tests
    class func isRunningUITests() -> Bool {
        if CommandLine.arguments.contains("--uitesting") {
            return true
        } else {
            return false
        }
    }
    
    /// Check if is running Unit or UI Tests
    class func isRunningTests() -> Bool {
        return isRunningUITests() || isRunningUnitTests()
    }
    
    class func keychainGroupName() -> String {
        guard let bundleId = Bundle.main.bundleIdentifier else { return "" }
        return bundleId
    }
}
