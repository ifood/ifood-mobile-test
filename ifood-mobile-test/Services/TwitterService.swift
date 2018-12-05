//
//  TwitterService.swift
//  ifood-mobile-test
//
//  Created by Marcio Garcia on 01/12/18.
//  Copyright © 2018 oxltech.com. All rights reserved.
//

import Foundation
import TwitterKit

class TwitterResponse {
    var userTimelineDataSource: TWTRUserTimelineDataSource!
}

class TwitterService {
    
    private var client: TWTRAPIClient!
    private var apiKeyRequestOperation: ApiKeyRequestOperation?
    private var apiSecretRequestOperation: ApiSecretRequestOperation?
    private var apiRequestsCompletionOperation: ApiRequestsCompletionOperation?
    private var operationQueue: OperationQueue?
    
    init(configuration: Configuration) {
        
        var apiKey: String = ""
        var apiSecret: String = ""
        
        let keychain = KeychainWorker(service: configuration.value(for: .bundleId))
        if let key = keychain.get(key: Keys.apiKey.rawValue), let secret = keychain.get(key: Keys.apiSecret.rawValue) {
            apiKey = key
            apiSecret = secret
            
            TWTRTwitter.sharedInstance().start(withConsumerKey: apiKey, consumerSecret: apiSecret)
            self.client = TWTRAPIClient()
        }
        else {
            let remoteConfig = RemoteConfigWorker()
            
            apiKeyRequestOperation = ApiKeyRequestOperation(remoteConfig: remoteConfig)
            apiSecretRequestOperation = ApiSecretRequestOperation(remoteConfig: remoteConfig)
            
            operationQueue = OperationQueue()
            operationQueue?.addOperation(apiKeyRequestOperation!)
            operationQueue?.addOperation(apiSecretRequestOperation!)
            
            apiRequestsCompletionOperation = ApiRequestsCompletionOperation()
            apiRequestsCompletionOperation?.addDependency(apiKeyRequestOperation!)
            apiRequestsCompletionOperation?.addDependency(apiSecretRequestOperation!)
            
            operationQueue?.addOperation(apiRequestsCompletionOperation!)
        }
        
        
    }
    
    public func requestUserTimeline(screenName: String, completionHandler: @escaping (TwitterResponse)->()) {
        
        if self.client == nil {
            guard let _completionOperation = self.apiRequestsCompletionOperation else {
                return
            }
            let timelineRequestOperation = TimelineRequestOperation(screenName: screenName, client: nil)
            timelineRequestOperation.addDependency(_completionOperation)
            operationQueue?.addOperation(timelineRequestOperation)
            
            let timelineCompletionOperation = BlockOperation {
                completionHandler(timelineRequestOperation.response!)
            }
            timelineCompletionOperation.addDependency(timelineRequestOperation)
            operationQueue?.addOperation(timelineCompletionOperation)
            
            
        }
        else {
            let dataSource = TWTRUserTimelineDataSource(screenName: screenName, apiClient: client)
            let response = TwitterResponse()
            response.userTimelineDataSource = dataSource
            completionHandler(response)
        }
    }
    
}

class ApiKeyRequestOperation: AsyncOperation {
    var remoteConfig: RemoteConfigWorker
    var apiKey: String = ""
    
    init(remoteConfig: RemoteConfigWorker) {
        self.remoteConfig = remoteConfig
    }
    
    override func execute() {
        guard self.isCancelled == false else {
            self.finish()
            return
        }
        
        remoteConfig.getString("api_key") { (value) in
            guard self.isCancelled == false else {
                self.finish()
                return
            }
            
            guard let _key = value else {
                self.finish()
                return
            }
            self.apiKey = _key
            self.finish()
        }
        
    }
}

class ApiSecretRequestOperation: AsyncOperation {
    var remoteConfig: RemoteConfigWorker
    var apiSecret: String = ""
    
    init(remoteConfig: RemoteConfigWorker) {
        self.remoteConfig = remoteConfig
    }
    
    override func execute() {
        guard self.isCancelled == false else {
            self.finish()
            return
        }
        
        remoteConfig.getString("api_secret") { (value) in
            guard self.isCancelled == false else {
                self.finish()
                return
            }
            
            guard let _secret = value else {
                self.finish()
                return
            }
            self.apiSecret = _secret
            self.finish()
        }
        
    }
}

class ApiRequestsCompletionOperation: AsyncOperation {
    var client: TWTRAPIClient?
    
    override func execute() {
        guard self.isCancelled == false else {
            self.finish()
            return
        }
        
        let apiKeyRequestOperation = self.dependencies[0] as? ApiKeyRequestOperation
        let apiSecretRequestOperation = self.dependencies[1] as? ApiSecretRequestOperation
        
        let apiKey = apiKeyRequestOperation!.apiKey
        let apiSecret = apiSecretRequestOperation!.apiSecret
        
        TWTRTwitter.sharedInstance().start(withConsumerKey: apiKey, consumerSecret: apiSecret)
        self.client = TWTRAPIClient()
        
        let configuration = Configuration()
        let keychain = KeychainWorker(service: configuration.value(for: .bundleId))
        keychain.set(key: Keys.apiKey.rawValue, value: apiKey)
        keychain.set(key: Keys.apiSecret.rawValue, value: apiSecret)
        
        self.finish()
    }
}

class TimelineRequestOperation: AsyncOperation {
    
    var screenName: String
    var client: TWTRAPIClient?
    var response: TwitterResponse?
    
    init(screenName: String, client: TWTRAPIClient?) {
        self.screenName = screenName
        self.client = client
    }
    
    override func execute() {
        guard self.isCancelled == false else {
            self.finish()
            return
        }
        
        let completionOperation = self.dependencies.first as? ApiRequestsCompletionOperation
        let dataSource = TWTRUserTimelineDataSource(screenName: screenName, apiClient: completionOperation!.client!)
        
        guard self.isCancelled == false else {
            self.finish()
            return
        }
        
        response = TwitterResponse()
        response?.userTimelineDataSource = dataSource
        self.finish()
    }
}
