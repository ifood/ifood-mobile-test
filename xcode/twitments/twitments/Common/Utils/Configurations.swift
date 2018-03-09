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
    
    func googleBaseURL() -> String {
        guard let baseURL = variables?["googleBaseURL"] as? String else {
            return "googleBaseURL key not found"
        }
        return baseURL
    }
    
    func googleToken() -> String {
        guard let googleToken = variables?["googleToken"] as? String else {
            return "googleToken key not found"
        }
        return googleToken
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
