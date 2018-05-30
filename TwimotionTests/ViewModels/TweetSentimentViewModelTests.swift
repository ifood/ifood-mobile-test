//
//  TweetSentimentViewModelTests.swift
//  TwimotionTests
//
//  Created by Antony on 30/05/18.
//  Copyright © 2018 Twimotion. All rights reserved.
//

import XCTest
import Nimble
import Quick
import RxSwift
import RxBlocking
import RxDataSources
import RxTest

@testable import Twimotion

class TweetSentimentViewModelTests: QuickSpec {
    
    override func spec() {
        super.spec()
        
        var disposeBag: DisposeBag!
        var subject: TweetSentimentViewModelType!
        var vmDelegate: TweetSentimentViewModelDelegateMock?
        var twitterDataSource: TwitterDataSourceMock!
        var naturalLangDataSource: NaturalLangDataSourceMock!
        
        beforeEach {
            disposeBag = DisposeBag()
            twitterDataSource = TwitterDataSourceMock()
            naturalLangDataSource = NaturalLangDataSourceMock()
            
            let vm = TweetSentimentViewModel(
                tweet: twitterDataSource.tweetMock,
                naturalLangDataSource: naturalLangDataSource
            )
            vmDelegate = TweetSentimentViewModelDelegateMock()
            vm.delegate = vmDelegate
            subject = vm
        }
        
        afterEach {
            subject = nil
            vmDelegate = nil
            disposeBag = nil
        }
        
        it("should show tweet formatted") {
            let tweet = twitterDataSource.tweetMock
            let result = try? subject.tweetDescription.toBlocking().first()!
            expect(result) == "\"\(tweet.text)\" - @\(tweet.user.screenName)"
        }
        
        describe("when analise tweet sentiment") {
            
            it("should show correctly sentiment theme") {
                
                let scheduler = TestScheduler(initialClock: 0)
                let observerEmoji = scheduler.createObserver(String.self)
                let observerColor = scheduler.createObserver(UIColor.self)
                let observerDescription = scheduler.createObserver(String.self)
                
                subject.sentimentEmoji.subscribe(observerEmoji).disposed(by: disposeBag)
                subject.sentimentColor.subscribe(observerColor).disposed(by: disposeBag)
                subject.sentimentDescription.subscribe(observerDescription).disposed(by: disposeBag)
                
                scheduler.start()
                
                // happy
                naturalLangDataSource.getSentimentData = Observable.just(Sentiment.happy)
                subject.loadSentiment.onNext(())
                //neutral
                naturalLangDataSource.getSentimentData = Observable.just(Sentiment.neutral)
                subject.loadSentiment.onNext(())
                //sad
                naturalLangDataSource.getSentimentData = Observable.just(Sentiment.sad)
                subject.loadSentiment.onNext(())
                
                // asserts emojis
                let resultEmojis = observerEmoji.events.map { $0.value.element! }
                expect(resultEmojis) == ["😃", "😐", "😔"]
                
                // asserts colors
                let resultColors = observerColor.events.map { $0.value.element! }
                expect(resultColors) == [
                    Styles.colors.happySentiment,
                    Styles.colors.neutralSentiment,
                    Styles.colors.sadSentiment
                ]
                
                // asserts Descriptions
                let resultDescription = observerDescription.events.map { $0.value.element! }
                expect(resultDescription) == [
                    L10n.TweetSentiment.happyMessage,
                    L10n.TweetSentiment.neutralMessage,
                    L10n.TweetSentiment.sadMessage
                ]
            }
            
        }
        
        
        describe("when user try to dismiss popup") {
            it("show notify delegate") {
                subject.dismissEvent.onNext(())
                expect(vmDelegate?.dismissedPopup) == true
            }
        }
        
    }
    
}


class TweetSentimentViewModelDelegateMock: TweetSentimentViewModelDelegate {
    var dismissedPopup = false
    func didDismissedPopup() {
        dismissedPopup = true
    }
}
