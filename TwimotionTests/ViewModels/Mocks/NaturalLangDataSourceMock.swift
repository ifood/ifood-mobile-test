//
//  NaturalLangDataSourceMock.swift
//  TwimotionTests
//
//  Created by Antony on 30/05/18.
//  Copyright © 2018 Twimotion. All rights reserved.
//

import Foundation
import RxSwift

@testable import Twimotion

class NaturalLangDataSourceMock: NaturalLangDataSourceType {
    
    var getSentimentData = Observable<Sentiment>.just(Sentiment.happy)
    
    func getSentiment(text: String) -> Observable<Sentiment> {
        return getSentimentData
    }
    
}

