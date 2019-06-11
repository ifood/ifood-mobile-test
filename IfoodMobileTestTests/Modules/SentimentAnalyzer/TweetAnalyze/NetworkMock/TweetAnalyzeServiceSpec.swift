//
//  TweetAnalyzeServiceSpec.swift
//  IfoodMobileTestTests
//
//  Created by André Vieira on 10/06/19.
//  Copyright © 2019 André Vieira. All rights reserved.
//

import Foundation
import RxSwift

@testable import IfoodMobileTest

final class TweetAnalyzeServiceMock: TweetAnalyzeService {
    
    private var sentmentAnalyze = SentimentAnalyzeModel(language: "en",
                                                sentiment: DocumentSentimentModel(magnitude: 0.0, score: 0.0))
    
    var score = 0.0
    var error = false
    
    func getFeeling(tweet: String) -> Observable<SentimentAnalyzeModel> {
        
        if error {
            return Observable.error(DataError.generic(message: ""))
        }
        
        sentmentAnalyze.sentiment?.score = score
        return Observable.just(sentmentAnalyze)
    }
}
