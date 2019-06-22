//
//  SentimentAnalysis.swift
//  TwitterAnalyzer
//
//  Created by Bruno Vieira on 19/06/19.
//  Copyright © 2019 Bruno Vieira. All rights reserved.
//

import UIKit

class SentimentAnalysis: Codable {
    struct Analysis: Codable {
        let score: Double?
        
        enum CodingKeys: String, CodingKey {
            case score
        }
    }
    
    let analysis: Analysis?
    
    enum CodingKeys: String, CodingKey {
        case analysis = "documentSentiment"
    }
}
