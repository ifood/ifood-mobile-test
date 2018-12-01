//
//  NetworkManager.swift
//  ifood-devtest
//
//  Created by Rafael Zilião on 29/11/18.
//  Copyright © 2018 Rafael Zilião. All rights reserved.
//

import Foundation
import Moya

public final class NetworkManager {
    static let apiKey = KeyManager.shared.googleCloudNLP
    static let shared = NetworkManager()
    static let provider = MoyaProvider<SentimentAnalysisApi>(plugins: [NetworkLoggerPlugin(verbose: true)])
    
    fileprivate init () {
        
    }
    
}

