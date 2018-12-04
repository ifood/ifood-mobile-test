//
//  FirebaseWorker.swift
//  ifood-mobile-test
//
//  Created by Marcio Garcia on 04/12/18.
//  Copyright © 2018 oxltech.com. All rights reserved.
//

import Foundation
import Firebase

class FirebaseWorker {
    
    static let shared: FirebaseWorker = FirebaseWorker()
    
    private init() {}
    
    public func configure(environment: EnvironmentType) {
        var firebaseConfigFile: String? = nil
        
        switch environment {
        case .debug:
            firebaseConfigFile = Bundle.main.path(forResource: "GoogleService-Info-debug", ofType: "plist")
        case .staging:
            firebaseConfigFile = Bundle.main.path(forResource: "GoogleService-Info-staging", ofType: "plist")
        case .release:
            firebaseConfigFile = Bundle.main.path(forResource: "GoogleService-Info-release", ofType: "plist")
        }
        
        if let configFile = firebaseConfigFile {
            if let options = FirebaseOptions(contentsOfFile: configFile) {
                FirebaseApp.configure(options: options)
            }
        }
    }
    
    public func remoteConfig() -> RemoteConfig {
        return RemoteConfig.remoteConfig()
    }
}

class RemoteConfigWorker {
    
    var remoteConfig: RemoteConfig
    
    init() {
        self.remoteConfig = FirebaseWorker.shared.remoteConfig()
    }
    
    public func getString(_ key: String, completion: @escaping (_ value: String?) -> ()) {
        self.remoteConfig.fetch(withExpirationDuration: 0) { (status, error) in
            if status != RemoteConfigFetchStatus.failure {
                self.remoteConfig.activateFetched()
                completion(self.remoteConfig[key].stringValue)
            }
        }
    }
}
