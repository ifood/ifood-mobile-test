//
//  TweetAnalyzeService.swift
//  IfoodMobileTest
//
//  Created by André Vieira on 10/06/19.
//  Copyright © 2019 André Vieira. All rights reserved.
//

import Foundation
import RxSwift

protocol TweetAnalyzeService {
    func getFeeling(tweet: String) -> Observable<SentimentAnalyzeModel>
}

final class TweetAnalyzeServiceImpl: TweetAnalyzeService {
    typealias Target = SentimentalAnalyzerTargetType
    private var provider: ProviderType<Target>
    
    init(provider: ProviderType<Target> = RequestProvider<SentimentalAnalyzerTargetType>()) {
        self.provider = provider
    }
    
    func getFeeling(tweet: String) -> Observable<SentimentAnalyzeModel> {
        return provider.requestObject(Target.getFeeling(tweet))
    }
    
}
