//
//  GCNaturalLanguageAPIResponse.swift
//  TwitterSentiments
//
//  Created by Marina Azevedo on 14/07/19.
//  Copyright © 2019 Marina Azevedo. All rights reserved.
//

import Foundation
import RxSwift

protocol NaturalLangDataSourceType {
    func getSentiment(text: String) -> Observable<Sentiment>
}

class NaturalLangDataSource: NaturalLangDataSourceType {
    
    lazy var naturalLangApi = HttpService<NaturalLangAPI>()

    func getSentiment(text: String) -> Observable<Sentiment> {
        return naturalLangApi.rx
            .request(NaturalLangAPI.analyzeSentiment(text: text))
            .map(DocumentSentimentResponse.self)
            .map { $0.documentSentiment.sentiment }
            .asObservable()
    }
}
