//
//  EnvironmentConfigs.swift
//  IfoodMobileTest
//
//  Created by André Vieira on 10/06/19.
//  Copyright © 2019 André Vieira. All rights reserved.
//

import Foundation

struct EnvironmentConfigs: Codable {
    var twitterBaseUrl: URL
    var twitterApiKey: String
    var twitterApiSecretKey: String
    var googleBaseUrl: URL
    var googleApiKey: String
    
    var twiiterBasicToken: String {
        return "\(twitterApiKey):\(twitterApiSecretKey)"
            .data(using: .utf8)?
            .base64EncodedString() ?? ""
    }
}

extension EnvironmentConfigs {
    static func load() -> EnvironmentConfigs {
        guard let envDic = Bundle.main.infoDictionary?["Environment"] as? NSDictionary else {
            fatalError("there isn't environment defined in info.plist")
        }
        
        do {
            return try JSONDecoder().decode(EnvironmentConfigs.self, from: envDic)
        } catch {
            fatalError("couldn’t be completed parse \(envDic.description) to Environment")
        }
    }
}
