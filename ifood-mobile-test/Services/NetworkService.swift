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
                                  completion: @escaping (String?, NSError?) -> ()) {
        
        let failure = { (error: NSError) in
            completion(nil, error)
        }
        
        let success = { (responseData: Data?) in
            guard let data = responseData else {
                completion(nil, nil)
                return
            }
            
            do {
//                let reviewsResponse = try JSONDecoder().decode(ReviewsResponse.self, from: data)
//                completion(reviewsResponse.data, nil)
            }
            catch {
                completion(nil, NSError(domain: "Error", code: 999, userInfo: ["message" : "Invalid payload format"]))
            }
        }
        
        var parameters: [String: Any] = [
            "field1": "",
            "field2": ""
        ]
        
        self.service.get("/analysis",
                         parameters: parameters,
                         encoding: .queryString,
                         headers: service.defaultHeader(),
                         success: success,
                         failure: failure)
    }
    
}


