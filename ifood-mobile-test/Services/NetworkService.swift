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
    
    init(restService: RestService) {
        self.service = restService
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
        
        self.service.post("/v1/documents:analyzeSentiment?key=AIzaSyDnrjzs20I2q68ufSfcgsBKrMur8Iegbmo",
                          parameters: parameters,
                          encoding: .json,
                          headers: service.defaultHeader(),
                          success: success,
                          failure: failure)
    }
    
}


