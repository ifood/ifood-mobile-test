//
//  NaturalLanguageDataSource.swift
//  Twimotion
//
//  Created by Antony on 27/05/18.
//  Copyright © 2018 Twimotion. All rights reserved.
//

import Foundation
import RxSwift

protocol NaturalLangDataSourceType {
    func getSentiment(text: String) -> Observable<Sentiment>
}

class NaturalLangDataSource: NaturalLangDataSourceType {

    lazy var naturalLangApi = HttpService<NaturalLangAPI>()

    // MARK: - NaturalLanDataSourceType

    func getSentiment(text: String) -> Observable<Sentiment> {
        return naturalLangApi.rx
            .request(NaturalLangAPI.analyzeSentiment(text: text))
            .map(DocumentSentimentPayload.self)
            .map { $0.documentSentiment.sentiment }
            .asObservable()
    }
}
