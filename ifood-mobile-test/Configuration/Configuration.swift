//
//  Configuration.swift
//  ifood-mobile-test
//
//  Created by Marcio Garcia on 30/11/18.
//  Copyright © 2018 oxltech.com. All rights reserved.
//

import Foundation

public enum PlistKey: String {
    case environment    = "Environment"
    case baseUrl        = "BaseUrl"
    case apiProtocol    = "ApiProtocol"
    case apiKey         = "ApiKey"
    case apiSecret      = "ApiSecret"
    case appVersion     = "AppVersion"
}

public enum EnvironmentType: String {
    case debug      =  "debug"
    case staging    =  "staging"
    case release    =  "release"
}

public struct Configuration {
    
    private var infoDictionary: [String: Any] {
        get {
            if let dict = Bundle.main.infoDictionary {
                return dict
            }
            else {
                fatalError("plist file not found")
            }
        }
    }
    
    public var environment: EnvironmentType {
        let envString = self.value(for: .environment)
        if let envType = EnvironmentType(rawValue: envString) {
            return envType
        }
        return EnvironmentType.release
    }
    
    public func value(for key: PlistKey) -> String {
        return self.infoDictionary[key.rawValue] as! String
    }
    
}


