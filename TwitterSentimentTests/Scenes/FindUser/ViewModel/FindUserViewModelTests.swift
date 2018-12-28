//
//  FindUserViewModelTests.swift
//  TwitterSentimentTests
//
//  Created by Jean Vinge on 28/12/18.
//  Copyright © 2018 Jean Vinge. All rights reserved.
//

@testable import TwitterSentiment

import Utility
import RxSwift
import XCTest
import RxBlocking

class FindUserViewModelTests: XCTestCase {
    
    private func createInput(trigger: Observable<String>) -> FindUserViewModel.Input {
        return FindUserViewModel.Input(searchUserName: trigger.asDriverOnErrorJustComplete())
    }
    
    private func createViewModel(with useCase: TwitterUseCaseMock) -> FindUserViewModel {
        return FindUserViewModel(useCase: useCase, coordinator: Coordinator(FindUserViewController()))
    }
    
    var disposeBag = DisposeBag()
    
    override func setUp() {
        self.disposeBag = DisposeBag()
    }
    
    func test_transform_toUserTweetList_with_success() {
        
        let useCase = TwitterUseCaseMock(.success)
        let viewModel = self.createViewModel(with: useCase)
        
        let trigger = PublishSubject<String>()
        let input = createInput(trigger: trigger)
        let output = viewModel.transform(input: input)
        
        output.toUserTweetList.drive().disposed(by: self.disposeBag)
        
        trigger.onNext("Test")
        
        do {
            let user = try output.toUserTweetList.toBlocking().first()
            
            XCTAssertNotNil(user)
            XCTAssertTrue(user?.name == "UOL")
            
        } catch {
        }
        
        XCTAssert(useCase.ensureAuthenticationCalled)
        XCTAssert(useCase.authenticateCalled)
        XCTAssert(useCase.userMethodCalled)
    }
    
    func test_transform_isFetching() {
        
        let useCase = TwitterUseCaseMock(.success)
        let viewModel = self.createViewModel(with: useCase)
        
        let trigger = PublishSubject<String>()
        let output = viewModel.transform(input: createInput(trigger: trigger))
        
        let expectedFetching = [true, false]
        var actualFetching: [Bool] = []
        
        output.fetching.do(onNext: { actualFetching.append($0) }, onSubscribe: { actualFetching.append(true) }).drive().disposed(by: self.disposeBag)
        trigger.onNext("Test")
        
        XCTAssertEqual(actualFetching, expectedFetching)
    }
    
    func test_transform_toUserTweetList_with_failure() {
        
        let useCase = TwitterUseCaseMock(.failure)
        let viewModel = self.createViewModel(with: useCase)
        
        let trigger = PublishSubject<String>()
        let input = createInput(trigger: trigger)
        let output = viewModel.transform(input: input)
        
        output.toUserTweetList.drive().disposed(by: self.disposeBag)
        
        trigger.onNext("Test")
        
        XCTAssert(useCase.ensureAuthenticationCalled)
        XCTAssert(useCase.authenticateCalled)
        XCTAssert(useCase.userMethodCalled)
    }
}
