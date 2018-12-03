//
//  URL+Root.swift
//  SentimentAnalysis
//
//  Created by Thales Frigo on 27/11/18.
//  Copyright © 2018 Thales Frigo. All rights reserved.
//

import Foundation

extension URL {
    
    init(staticString string: StaticString) {
        guard let url = URL(string: "\(string)") else {
            preconditionFailure("Invalid static URL string: \(string)")
        }
        
        self = url
    }
}

extension URL {
    
    struct TwitterApi {
        static let search = URL(staticString: "https://api.twitter.com/1.1/search/tweets.json")
    }
    
}
