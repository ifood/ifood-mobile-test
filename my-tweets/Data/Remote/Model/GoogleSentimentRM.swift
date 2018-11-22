//
//  GoogleSentimentRM.swift
//  my-tweets
//
//  Created by Gabriel Catice on 22/11/18.
//  Copyright © 2018 Gabriel Catice. All rights reserved.
//

import Foundation

struct GoogleSentimentRM: Codable {
    let documentSentiment: SentimentRM?
    let language: String?
    let sentences: [SentenceRM]?
    
    enum CodingKeys: String, CodingKey {
        case documentSentiment = "documentSentiment"
        case language = "language"
        case sentences = "sentences"
    }
}

struct SentimentRM: Codable {
    let magnitude: Double?
    let score: Double?
    
    enum CodingKeys: String, CodingKey {
        case magnitude = "magnitude"
        case score = "score"
    }
}

struct SentenceRM: Codable {
    let sentiment: SentimentRM?
    let text: TextRM?
    
    enum CodingKeys: String, CodingKey {
        case sentiment = "sentiment"
        case text = "text"
    }
}

struct TextRM: Codable {
    let beginOffset: Int?
    let content: String?
    
    enum CodingKeys: String, CodingKey {
        case beginOffset = "beginOffset"
        case content = "content"
    }
}

extension GoogleSentimentRM {
    func toSentenceDM(with sentence: String) -> Sentence {
        return Sentence(text: sentence, score: documentSentiment?.score)
    }
}
