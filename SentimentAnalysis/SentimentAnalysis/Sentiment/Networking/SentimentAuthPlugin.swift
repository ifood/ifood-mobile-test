//
//  SentimentAuthPlugin.swift
//  SentimentAnalysis
//
//  Created by Thales Frigo on 01/12/18.
//  Copyright © 2018 Thales Frigo. All rights reserved.
//

import Foundation
import Moya

struct SentimentAuthPlugin: PluginType {
    
    let key: String
    
    init(key: String) {
        self.key = key
    }
    
    func prepare(_ request: URLRequest, target: TargetType) -> URLRequest {
        let newUrlString = request.url!.absoluteString + "?key=\(key)"
        var newRequest = request
        newRequest.url = URL(string: newUrlString)
        
        return newRequest
    }
}
