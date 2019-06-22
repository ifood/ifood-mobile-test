//
//  Environment.swift
//  TwitterAnalyzer
//
//  Created by Bruno Vieira on 21/06/19.
//  Copyright © 2019 Bruno Vieira. All rights reserved.
//

import Foundation

protocol EnvironmentProtocol {
    var googleApiToken: String { get }
    var twitterApiToken: String { get }
    var twitterSecretApiToken: String { get }
    static var shared: EnvironmentProtocol { get }
}

class Environment: EnvironmentProtocol {
    var googleApiToken: String
    var twitterApiToken: String
    var twitterSecretApiToken: String
    
    // MARK: - Singleton
    static var shared: EnvironmentProtocol = Environment()

    // For testing purposes, tokens were stored in the Plist file. Real world applications must increase security by omitting/encrypting this information from the source code.
    init() {
        let environmentDictionary = Bundle.main.object(forInfoDictionaryKey: "Environment") as? NSDictionary
        googleApiToken = environmentDictionary?["google_api_key"] as? String ?? ""
        twitterApiToken = environmentDictionary?["twitter_api_key"] as? String ?? ""
        twitterSecretApiToken = environmentDictionary?["twitter_api_secret_key"] as? String ?? ""
    }
}

