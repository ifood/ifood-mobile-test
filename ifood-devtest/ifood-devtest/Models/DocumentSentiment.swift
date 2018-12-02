//
//  DocumentSentiment.swift
//  ifood-devtest
//
//  Created by Rafael Zilião on 29/11/18.
//  Copyright © 2018 Rafael Zilião. All rights reserved.
//

import Foundation

struct Sentiment {
    let language: String?
    let documentSentiment: DocumentSentiment?
}

extension Sentiment: Decodable {
    
    struct DocumentSentiment: Codable {
        let magnitude: Double
        let score: Double
    }
    
    enum SentimentCodingKeys: String, CodingKey {
        case language
        case documentSentiment
    }
    
    init(from decoder: Decoder) throws {
        let container = try decoder.container(keyedBy: SentimentCodingKeys.self)
        language = try? container.decode(String.self, forKey: .language)
        documentSentiment = try? container.decode(DocumentSentiment.self, forKey: .documentSentiment)
    }
    
    public static func requestSentimentAnalysisTweet(tweet: String,
                                                     success: @escaping (Sentiment) -> Void,
                                                     failure: @escaping (Error) -> Void = {_ in }) {
        
        NetworkManager.provider.request(.text(text: tweet)) { result in
            switch result {
            case let .success(response):
                do {
                    let results = try JSONDecoder().decode(Sentiment.self, from: response.data)
                    print(results)
                    DispatchQueue.main.async {
                        success(results)
                    }
                } catch let err {
                    print(err)
                }
            case let .failure(error):
                DispatchQueue.main.async {
                    failure(error)
                }
            }
        }
        
    }

}
