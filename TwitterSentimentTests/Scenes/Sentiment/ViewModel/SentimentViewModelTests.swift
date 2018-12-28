//
//  SentimentViewModelTests.swift
//  TwitterSentimentTests
//
//  Created by Jean Vinge on 28/12/18.
//  Copyright © 2018 Jean Vinge. All rights reserved.
//

@testable import TwitterSentiment

import RxSwift
import Domain
import Utility
import Resources
import XCTest

class SentimentViewModelTests: XCTestCase {

    private func createInput(trigger: Observable<Void>, onDismiss: Observable<Void>) -> SentimentViewModel.Input {
        return SentimentViewModel.Input(trigger: trigger.asDriverOnErrorJustComplete(), onDismiss: onDismiss.asDriverOnErrorJustComplete())
    }
    
    private func createViewModel(tweet: Tweet, with useCase: TextAnalizerUseCaseMock) -> SentimentViewModel {
        return SentimentViewModel(tweet: tweet, useCase: useCase, coordinator: Coordinator(SentimentViewController()))
    }
    
    let tweet = Tweet("1", text: "teste teste", user: TwitterUser("1", name: "test", screenName: "test", profileImageURL: URL(string: "www.fake.com.br")!), createdAt: Date())
    
    var disposeBag = DisposeBag()
    
    override func setUp() {
        self.disposeBag = DisposeBag()
    }
    
    func test_correct_sentiment(useCase: TextAnalizerUseCaseMock) {
        let viewModel = self.createViewModel(tweet: self.tweet, with: useCase)
        
        let trigger = PublishSubject<Void>()
        let onDismiss = PublishSubject<Void>()
        let input = createInput(trigger: trigger, onDismiss: onDismiss)
        let output = viewModel.transform(input: input)
        
        output.showSentiment.drive().disposed(by: self.disposeBag)
        output.onDismiss.drive().disposed(by: self.disposeBag)
        
        trigger.onNext(())
        
        do {
            if let sentiment = try output.showSentiment.toBlocking().first() {
                XCTAssertEqual(sentiment, sentimentFactory(sentiment: useCase.sentiment))
            } else {
                XCTFail("Sentiment can't be nil")
            }
            
        } catch {
            XCTFail("Sentiment can't be nil")
        }
        
        XCTAssert(useCase.sentimentCalled)
    }

    func test_transform_toSentiment_neutral() {
        self.test_correct_sentiment(useCase: TextAnalizerUseCaseMock(sentiment: .neutral))
    }
    
    func test_transform_toSentiment_happy() {
        self.test_correct_sentiment(useCase: TextAnalizerUseCaseMock(sentiment: .happy))
    }
    
    func test_transform_toSentiment_sad() {
        self.test_correct_sentiment(useCase: TextAnalizerUseCaseMock(sentiment: .sad))
    }
    
    func test_transform_isFetching() {
        
        let viewModel = self.createViewModel(tweet: self.tweet, with: TextAnalizerUseCaseMock(sentiment: .neutral))
        
        let trigger = PublishSubject<Void>()
        let onDismiss = PublishSubject<Void>()
        let output = viewModel.transform(input: createInput(trigger: trigger, onDismiss: onDismiss))
        
        let expectedFetching = [true, false]
        var actualFetching: [Bool] = []
        
        output.fetching.do(onNext: { actualFetching.append($0) }, onSubscribe: { actualFetching.append(true) }).drive().disposed(by: self.disposeBag)
        trigger.onNext(())
        
        XCTAssertEqual(actualFetching, expectedFetching)
    }
}
