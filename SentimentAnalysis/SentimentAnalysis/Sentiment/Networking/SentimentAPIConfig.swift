//
//  SentimentAPIConfig.swift
//  SentimentAnalysis
//
//  Created by Thales Frigo on 01/12/18.
//  Copyright © 2018 Thales Frigo. All rights reserved.
//

import Foundation
import Keys

struct SentimentAPIConfig {
    
    static let baseURL = URL(string: "https://language.googleapis.com/v1")!
    
    static func makeApiKey() -> String {
        let keys = SentimentAnalysisKeys()
        return keys.sentimentApiKey
    }
}
