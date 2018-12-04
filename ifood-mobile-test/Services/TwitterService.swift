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
    
    init(environment: EnvironmentType) {
        
        var apiKey: String = ""
        var apiSecret: String = ""
        
        let keychain = KeychainWorker(service: "com.oxltech.ifood-mobile-test")
        if let key = keychain.get(key: "api-key"), let secret = keychain.get(key: "api-secret") {
            apiKey = key
            apiSecret = secret
            
            TWTRTwitter.sharedInstance().start(withConsumerKey: apiKey, consumerSecret: apiSecret)
            self.client = TWTRAPIClient()
        }
        else {
            
            let remoteConfig = RemoteConfigWorker(environment: environment)
            
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
            print("************* self.client == nil")
            guard let _completionOperation = self.apiRequestsCompletionOperation else {
                print("************* self.completionOperation == nil")
                return
            }
            print("************* self.completionOperation executing: \(self.apiRequestsCompletionOperation?.isExecuting)")
            print("************* self.completionOperation finished: \(self.apiRequestsCompletionOperation?.isFinished)")
            let timelineRequestOperation = TimelineRequestOperation(screenName: screenName, client: nil)
            timelineRequestOperation.addDependency(_completionOperation)
            operationQueue?.addOperation(timelineRequestOperation)
            
            let timelineCompletionOperation = BlockOperation {
                print("************* timelineCompletion: \(timelineRequestOperation.response.debugDescription)")
                completionHandler(timelineRequestOperation.response!)
            }
            timelineCompletionOperation.addDependency(timelineRequestOperation)
            operationQueue?.addOperation(timelineCompletionOperation)
            
            
        }
        else {
            print("************* self.client != nil")
            let dataSource = TWTRUserTimelineDataSource(screenName: screenName, apiClient: client)
            let response = TwitterResponse()
            response.userTimelineDataSource = dataSource
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
                print("************* Error")
                return
            }
            self.apiKey = _key
            print("************* api_key: \(_key)")
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
                print("************* Error")
                self.finish()
                return
            }
            self.apiSecret = _secret
            print("************* api_secret: \(_secret)")
            self.finish()
        }
        
    }
}

class ApiRequestsCompletionOperation: AsyncOperation {
    var client: TWTRAPIClient?
    
    override func execute() {
        print("************* self.completionOperation execute")
        guard self.isCancelled == false else {
            print("************* self.completionOperation cancelled")
            self.finish()
            return
        }
        
        let apiKeyRequestOperation = self.dependencies[0] as? ApiKeyRequestOperation
        let apiSecretRequestOperation = self.dependencies[1] as? ApiSecretRequestOperation
        
        TWTRTwitter.sharedInstance().start(withConsumerKey: apiKeyRequestOperation!.apiKey,
                                           consumerSecret: apiSecretRequestOperation!.apiSecret)
        self.client = TWTRAPIClient()
        
        print("************* self.completionOperation self.client: \(self.client)")
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
        print("************* self.completionOperation execute")
        guard self.isCancelled == false else {
            self.finish()
            return
        }
        
        let completionOperation = self.dependencies.first as? ApiRequestsCompletionOperation
        let dataSource = TWTRUserTimelineDataSource(screenName: screenName, apiClient: completionOperation!.client!)
        print("************* datasource: \(dataSource.debugDescription)")
        guard self.isCancelled == false else {
            self.finish()
            return
        }
        
        response = TwitterResponse()
        response?.userTimelineDataSource = dataSource
        self.finish()
    }
}
