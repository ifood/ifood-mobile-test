//
//  NetworkManager.swift
//  twitter-mood
//
//  Created by Dennis Merli on 7/6/19.
//  Copyright © 2019 Dennis Merli. All rights reserved.
//

import Foundation
import Moya

final class NetworkManager {
    
    static let shared = NetworkManager()
    var isMoyaOnVerboseMode: Bool = false
    private(set) var currentEnvVars: EnvironmentVariables?
    
    private init() {
        loadEnvVars()
    }
    
    // MARK: Environment Variables
    var currentNetworkOperationMode: NetworkOperationMode {
        if ProjectUtils.isRunningTests() {
            return .stubResponse
        } else {
            return .normal
        }
    }
    
    private func loadEnvVars() {
        let loader = PlistLoader<EnvironmentVariables>(fileName: "Variables")
        do {
            let result = try loader.loadFromPlist()
            self.currentEnvVars = result
        } catch {
            fatalError("Could not load environment variables: \(error.localizedDescription)")
        }
    }
    
    // MARK: Moya configuration
    func createMoyaPlugins() -> [PluginType] {
        var plugins: [PluginType] = []
        if isMoyaOnVerboseMode {
            plugins.append(NetworkLoggerPlugin(verbose: true))
        }
        return plugins
    }
    
    func handleMoyaError(_ error: Error, response: Moya.Response) -> Error {
        return CustomError.errorFromService(with: response)
    }
    
    func twitterAuthToken() -> String? {
       let accessTokenService = AccessTokenService(keychainService: KeychainService(service: ProjectUtils.keychainGroupName()))
        do {
            guard let accessToken = try accessTokenService.loadAccessToken() else {
                return nil
            }
            return accessToken.accessToken
        } catch {
            return nil
        }
    }
}
