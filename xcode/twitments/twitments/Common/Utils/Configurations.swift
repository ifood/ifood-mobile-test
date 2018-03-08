//
//  Configurations.swift
//  twitments
//
//  Created by Ezequiel on 08/03/18.
//  Copyright © 2018 Ezequiel. All rights reserved.
//

import Foundation

enum AppTarget: String {
    case twitmentsTests = "twitmentsTests"
    case twitments = "twitments"
}
public class Configurations {
    
    static let shared = Configurations()
    private var variables: [String: AnyObject]?
    private var target: AppTarget = .twitments
    
    public enum ConfigurationError: Error {
        case FileNotFound
        case EnvironmentNotFound
    }
    
    func initializeFromFile() throws {
        
        let mainBundle = Bundle.main
        
        guard let path = mainBundle.path(forResource: "Configurations", ofType: "plist") else {
            throw ConfigurationError.FileNotFound
        }
        
        let configurations = NSDictionary(contentsOfFile: path)
        
        guard let config = mainBundle.infoDictionary?["TargetName"] as? String else {
            throw ConfigurationError.EnvironmentNotFound
        }
        
        guard let currentTarget = AppTarget.init(rawValue: config) else {
            throw ConfigurationError.EnvironmentNotFound
        }
        
        self.target = currentTarget
        
        guard let variablesFromFile = configurations?.object(forKey: target.rawValue) as? [String: AnyObject] else {
            throw ConfigurationError.EnvironmentNotFound
        }
        
        variables = variablesFromFile
    }
    
    func twitterBaseURL() -> String {
        guard let baseURL = variables?["twitterBaseURL"] as? String else {
            return "twitterBaseURL key not found"
        }
        return baseURL
    }
}
