//
//  SentimentAnalyzerResponse.swift
//  SentimentAnalysis
//
//  Created by Thales Frigo on 01/12/18.
//  Copyright © 2018 Thales Frigo. All rights reserved.
//

import Foundation

struct SentimentAnalyzerResponse: Codable {

    let document: Sentiment
    
    enum CodingKeys: String, CodingKey {
        case document = "documentSentiment"
    }
}
