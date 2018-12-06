//
//  NetworkService.swift
//  ifood-mobile-test
//
//  Created by Marcio Garcia on 30/11/18.
//  Copyright © 2018 oxltech.com. All rights reserved.
//

import Foundation

class NetworkService: DataService {
    
    private var service: RestService!
    private var apiKey: String?
    
    private let operationQueue = OperationQueue()
    private var gcKeyOperation: GCApiKeyRequestOperation?
    private var gcRequestOperation: BlockOperation?

    init(restService: RestService) {
        self.service = restService
        
        let configuration = Configuration()
        let keychain = KeychainWorker(service: configuration.value(for: .bundleId))
        if let key = keychain.get(key: Keys.gcKey.rawValue) {
            self.apiKey = key
        }
        else {
            let remoteConfig = RemoteConfigWorker()
            gcKeyOperation = GCApiKeyRequestOperation(remoteConfig: remoteConfig)
            gcKeyOperation?.completionBlock = { [weak self] in
                self?.apiKey = self?.gcKeyOperation?.apiKey
            }
            operationQueue.addOperation(gcKeyOperation!)
        }
    }
    
    func requestSentimentAnalysis(text: String,
                                  completion: @escaping (AnalyzedSentiment?, NSError?) -> ()) {
        
        let failure = { (error: NSError) in
            completion(nil, error)
        }
        
        let success = { (responseData: Data?) in
            guard let data = responseData else {
                completion(nil, nil)
                return
            }
            
            do {
                let analyzedSentiment = try JSONDecoder().decode(AnalyzedSentiment.self, from: data)
                completion(analyzedSentiment, nil)
            }
            catch {
                completion(nil, NSError(domain: "Error",
                                        code: 999,
                                        userInfo: ["message" : NSLocalizedString("Invalid payload format", comment: "")]))
            }
        }
        
        let parameters: [String: Any] = [
            "document": [
                "type": "PLAIN_TEXT",
                "content": text
            ],
            "encodingType": "UTF8"
        ]
        //AIzaSyDnrjzs20I2q68ufSfcgsBKrMur8Iegbmo
        if let apiKey = self.apiKey {
            self.service.post("/v1/documents:analyzeSentiment?key=\(apiKey)",
                              parameters: parameters,
                              encoding: .json,
                              headers: service.defaultHeader(),
                              success: success,
                              failure: failure)
        }
        else {
            gcRequestOperation = BlockOperation { [weak self] in
                guard let apiKey = self?.gcKeyOperation?.apiKey else {
                    return
                }
                self?.service.post("/v1/documents:analyzeSentiment?key=\(apiKey)",
                                  parameters: parameters,
                                  encoding: .json,
                                  headers: self?.service.defaultHeader(),
                                  success: success,
                                  failure: failure)
            }
            gcRequestOperation?.addDependency(self.gcKeyOperation!)
            self.operationQueue.addOperation(gcRequestOperation!)
        }
        
        
        
    }
    
}

class GCApiKeyRequestOperation: AsyncOperation {
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
        
        remoteConfig.getString("gc_key") { (value) in
            guard self.isCancelled == false else {
                self.finish()
                return
            }
            
            guard let _key = value else {
                self.finish()
                return
            }
            
            self.apiKey = _key
            
            let configuration = Configuration()
            let keychain = KeychainWorker(service: configuration.value(for: .bundleId))
            keychain.set(key: Keys.gcKey.rawValue, value: _key)
            
            self.finish()
        }
        
    }
}
