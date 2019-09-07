//
//  TweetListViewModelTests.swift
//  TwitterSentimentTests
//
//  Created by Jean Vinge on 28/12/18.
//  Copyright © 2018 Jean Vinge. All rights reserved.
//

@testable import TwitterSentiment

import Domain
import Utility
import RxSwift
import RxBlocking
import XCTest

class TweetListViewModelTests: XCTestCase {

    private func createInput(trigger: Observable<Void>, onTapTweet: Observable<Tweet>) -> TweetListViewModel.Input {
        return TweetListViewModel.Input(trigger: trigger.asDriverOnErrorJustComplete(), onTapTweet: onTapTweet.asDriverOnErrorJustComplete())
    }
    
    private func createViewModel(user: TwitterUser, with useCase: TwitterUseCaseMock) -> TweetListViewModel {
        return TweetListViewModel(user: user, useCase: useCase, router: ScreenRouter(TweetListViewController()))
    }
    
    let user = TwitterUser("1", name: "Test", screenName: "test", profileImageURL: URL(string: "www.fake.com.br")!)
    
    var disposeBag = DisposeBag()
    
    override func setUp() {
        self.disposeBag = DisposeBag()
    }

    func test_transform_List_with_success() {
        
        let useCase = TwitterUseCaseMock(.success)
        
        let viewModel = self.createViewModel(user: self.user, with: useCase)
        
        let trigger = PublishSubject<Void>()
        let onTapTweet = PublishSubject<Tweet>()
        
        let input = createInput(trigger: trigger, onTapTweet: onTapTweet)
        let output = viewModel.transform(input: input)
        
        output.title.drive().disposed(by: self.disposeBag)
        output.onTapTweet.drive().disposed(by: self.disposeBag)
        output.tweetList.drive().disposed(by: self.disposeBag)
        
        trigger.onNext(())
        
        do {
            let list = try output.tweetList.toBlocking().first()?.first?.items
            let title = try output.title.toBlocking().first()
            XCTAssertEqual(title, "@test")
            XCTAssert((list?.count ?? 0) > 0)
            
            if let tweet = list?.first {
                onTapTweet.onNext(tweet)
            } else {
                XCTFail("tweet list can't be nil")
            }
        } catch {
            XCTFail("could not load tweet list")
        }
        
        XCTAssert(useCase.ensureAuthenticationCalled)
        XCTAssert(useCase.authenticateCalled)
        XCTAssert(useCase.latestTweetsCalled)
    }
    
    func test_transform_isFetching() {
        
        let useCase = TwitterUseCaseMock(.success)
        let viewModel = self.createViewModel(user: self.user, with: useCase)
        
        let trigger = PublishSubject<Void>()
        let onTapTweet = PublishSubject<Tweet>()
        
        let output = viewModel.transform(input: createInput(trigger: trigger, onTapTweet: onTapTweet))
        
        let expectedFetching = [true, false]
        var actualFetching: [Bool] = []
        
        output.fetching.do(onNext: { actualFetching.append($0) }, onSubscribe: { actualFetching.append(true) }).drive().disposed(by: self.disposeBag)
        trigger.onNext(())
        
        XCTAssertEqual(actualFetching, expectedFetching)
    }
    
    func test_transform_toUserTweetList_with_failure() {
        
        let useCase = TwitterUseCaseMock(.failure)
        let viewModel = self.createViewModel(user: self.user, with: useCase)
        
        let trigger = PublishSubject<Void>()
        let onTapTweet = PublishSubject<Tweet>()
        
        let input = createInput(trigger: trigger, onTapTweet: onTapTweet)
        let output = viewModel.transform(input: input)
        
        output.tweetList.drive().disposed(by: self.disposeBag)
        
        trigger.onNext(())
        
        XCTAssert(useCase.ensureAuthenticationCalled)
        XCTAssert(useCase.authenticateCalled)
        XCTAssert(useCase.latestTweetsCalled)
    }
}
