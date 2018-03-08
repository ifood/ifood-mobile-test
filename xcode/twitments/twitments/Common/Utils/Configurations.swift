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
        
        print(mainBundle.infoDictionary)
        print(configurations)
//        guard let variablesFromFile = configurations?.object(forKey: "twitments") as? [String: AnyObject] else {
//            throw ConfigurationError.EnvironmentNotFound
//        }
        
        variables = configurations as! [String : AnyObject]
    }
    
    func twitterBaseURL() -> String {
        guard let baseURL = variables?["twitterBaseURL"] as? String else {
            return "twitterBaseURL key not found"
        }
        return baseURL
    }
    
    func twitterKey() -> String {
        guard let key = variables?["twitterKey"] as? String else {
            return "twitterKey key not found"
        }
        return key.urlHostAllowed
    }
    
    func twitterSecret() -> String {
        guard let secret = variables?["twitterSecret"] as? String else {
            return "twitterSecret key not found"
        }
        return secret.urlHostAllowed
    }
}

class ConfigurationCreator {
    static func initializer() {
        do {
            try Configurations.shared.initializeFromFile()
        } catch Configurations.ConfigurationError.FileNotFound {
        } catch Configurations.ConfigurationError.EnvironmentNotFound {
        } catch {
            print("====> Erro: Não foi possível carregar as configurações do arquivo <=====")
        }
    }
}
