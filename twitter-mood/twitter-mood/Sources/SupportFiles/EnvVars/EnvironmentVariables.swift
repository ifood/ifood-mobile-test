//
//  EnvironmentVariables.swift
//  twitter-mood
//
//  Created by Dennis Merli on 7/7/19.
//  Copyright © 2019 Dennis Merli. All rights reserved.
//

import Foundation

final class EnvironmentVariables: Codable {
    let apiVariablesTwitter: APIEnvironmentVariables?
    let apiVariablesGoogle: APIEnvironmentVariables?
    
}

class APIEnvironmentVariables: Codable {
    let domain: String?
    let apiVersion: String?
    let apiKey: String?
    
    var baseURL: URL? {
        guard var domain = domain else { return nil }
        if let unwrappedAPIVersion = apiVersion {
            domain.append("/\(unwrappedAPIVersion)")
        }
        return URL(string: domain)
    }
    
    var baseURLWithoutVersion: URL? {
        guard let domain = domain else { return nil }
        return URL(string: domain)
    }
}
