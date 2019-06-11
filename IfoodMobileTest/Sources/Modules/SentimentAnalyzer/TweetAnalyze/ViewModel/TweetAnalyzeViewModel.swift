//
//  TweetAnalyzeViewModel.swift
//  IfoodMobileTest
//
//  Created by André Vieira on 10/06/19.
//  Copyright © 2019 André Vieira. All rights reserved.
//

import Foundation
import RxSwift

protocol TweetAnalyzeViewModelOutput {
    var bgColor: BehaviorSubject<UIColor> { get }
    var emoji: BehaviorSubject<String> { get }
    var errorMessage: BehaviorSubject<String?> { get }
    var showLoader: BehaviorSubject<Bool> { get }
}

protocol TweetAnalyzeViewModelInput {}

final class TweetAnalyzeViewModel: TweetAnalyzeViewModelOutput, TweetAnalyzeViewModelInput {
    
    var bgColor: BehaviorSubject<UIColor>
    var emoji: BehaviorSubject<String>
    var errorMessage: BehaviorSubject<String?>
    var showLoader: BehaviorSubject<Bool>
    
    private var service: TweetAnalyzeService
    private var bag = DisposeBag()
    
    init(tweet: String, service: TweetAnalyzeService = TweetAnalyzeServiceImpl()) {
        self.service = service
        bgColor = BehaviorSubject<UIColor>(value: .white)
        emoji = BehaviorSubject<String>(value: "")
        errorMessage = BehaviorSubject<String?>(value: nil)
        showLoader = BehaviorSubject<Bool>(value: false)
        fetchSentimentAnalyze(tweet: tweet)
    }
    
    private func fetchSentimentAnalyze(tweet: String) {
        showLoader.onNext(true)
        service.getFeeling(tweet: tweet).subscribe(onNext: {[weak self] sentiment in
            self?.analyzeSentiment(score: sentiment.sentiment?.score)
            self?.showLoader.onNext(false)
        }, onError: {[weak self] _ in
            self?.errorMessage.onNext(L10n.DefaultText.genericError)
            self?.showLoader.onNext(false)
        }).disposed(by: bag)
    }
    
    private func analyzeSentiment(score: Double?) {
        guard let score = score else {
            self.emoji.onNext("❌")
            self.bgColor.onNext(.white)
            return
        }
        var sentmentEmoji = ""
        var sentmentColor = UIColor.white
        switch score {
        case let score where score < 0:
            sentmentEmoji = "😔"
            sentmentColor = .blue
        case let score where score > 0:
            sentmentEmoji = "😃"
            sentmentColor = .yellow
        default:
            sentmentEmoji = "😐"
            sentmentColor = .gray
        }
        
        self.emoji.onNext(sentmentEmoji)
        self.bgColor.onNext(sentmentColor)
    }
}
