//
//  RemoteConfigWorker.swift
//  ifood-mobile-test
//
//  Created by Marcio Garcia on 05/12/18.
//  Copyright © 2018 oxltech.com. All rights reserved.
//

import Foundation
import Firebase

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
