//
//  SentimentDataSource.swift
//  my-tweets
//
//  Created by Gabriel Catice on 22/11/18.
//  Copyright © 2018 Gabriel Catice. All rights reserved.
//

import Foundation
import Moya
import RxMoya
import RxSwift

struct SentimentDataSource {
    let provider: MoyaProvider<GoogleProvider>
    
    init(provider: MoyaProvider<GoogleProvider>) {
        self.provider = provider
    }
    
    func analyzeSentiment(sentiment: String) -> Single<GoogleSentimentRM> {
        return self.provider.rx.request(.sentiment(text: sentiment))
            .mapDomainError()
            .mapDecodableEntity()
    }
}
