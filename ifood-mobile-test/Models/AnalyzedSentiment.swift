//
//  AnalyzedSentiment.swift
//  ifood-mobile-test
//
//  Created by Marcio Garcia on 01/12/18.
//  Copyright © 2018 oxltech.com. All rights reserved.
//

import Foundation

struct AnalyzedSentiment: Decodable {
    var language: String
    var documentSentiment: DocumentSentiment
    var sentences: [Sentence]
}

struct DocumentSentiment: Decodable {
    var magnitude: Double
    var score: Double
}

struct Sentence: Decodable {
    var text: Text
    var sentiment: Sentiment
}

struct Text: Decodable {
    var content: String
    var beginOffset: Int
}

struct Sentiment: Decodable {
    var magnitude: Double
    var score: Double
}

//func jsonObject() {
//
//    do {
//        let analyzeSentiment = try JSONDecoder().decode(AnalyzeSentiment.self, from: data)
//    }
//    catch {
//        print("Error")
//    }
//
//}

//{
//    "documentSentiment": {
//        "magnitude": 0.4,
//        "score": -0.4
//    },
//    "language": "en",
//    "sentences": [
//        {
//            "text": {
//                "content": "I'm very sad today",
//                "beginOffset": 0
//            },
//            "sentiment": {
//                "magnitude": 0.4,
//                "score": -0.4
//            }
//        }
//    ]
//}
