//
//  DataLoader.swift
//  SentimentAnalysis
//
//  Created by Thales Frigo on 27/11/18.
//  Copyright © 2018 Thales Frigo. All rights reserved.
//

import Foundation

class DataLoader {
    
    enum Result {
        case success(Data)
        case failure(Error)
    }
    
    let engine: NetworkEngine
    
    init(engine: NetworkEngine = URLSession.shared) {
        self.engine = engine
    }
    
    func load(from url: URL, completionHandler: @escaping (Result) -> Void) {
        engine.performRequest(for: url) { (data, response, error) in
            if let error = error {
                return completionHandler(.failure(error))
            }
            
            completionHandler(.success(data ?? Data()))
        }
    }
}
