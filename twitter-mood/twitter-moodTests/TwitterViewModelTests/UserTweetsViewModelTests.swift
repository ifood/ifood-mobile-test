//
//  UserTweetsViewModelTests.swift
//  twitter-moodTests
//
//  Created by Dennis Merli on 7/11/19.
//  Copyright © 2019 Dennis Merli. All rights reserved.
//

import Foundation
import XCTest
import RxSwift
import RxCocoa
import RxTest

@testable import twitter_mood

class UserTweetsViewModelTests: XCTestCase {
    
    var viewModel: UserTweetsViewModel!
    var disposeBag: DisposeBag!
    
    override func setUp() {
        super.setUp()
        viewModel = UserTweetsViewModel(username: "anitta", twitterService: TwitterService(operationMode: .stubResponse))
        disposeBag = DisposeBag()
    }
    
    override func tearDown() {
        super.tearDown()
        viewModel = nil
    }
    
    func testLoadUserTweets() {
        let scheduler = TestScheduler(initialClock: 0)
        let observer = scheduler.createObserver([UserTweetCellViewModel].self)
        
        scheduler.scheduleAt(100) {
            self.viewModel.userTweets.asObservable()
                .subscribe(observer)
                .disposed(by: self.disposeBag)
        }
        
        scheduler.scheduleAt(200) {
            self.viewModel.loadUserTweets()
        }
        
        scheduler.start()
        let results = observer.events.map { event in
            (event.value.element)!
        }
        
        XCTAssertEqual(results[0].count, 0)
        XCTAssertEqual(results[1].count, 2)
    }
    
    func testUsernameSetup() {
        XCTAssertEqual(viewModel.username, "anitta")
    }
    
    func testIsLoadingIndicator() {
        let scheduler = TestScheduler(initialClock: 0)
        let observer = scheduler.createObserver(Bool.self)
        
        scheduler.scheduleAt(100) {
            self.viewModel.isLoading.asObservable()
                .subscribe(observer)
                .disposed(by: self.disposeBag)
        }
        
        scheduler.scheduleAt(200) {
            self.viewModel.loadUserTweets()
        }
        
        scheduler.start()
        let results = observer.events.map { event in
            (event.value.element)!
        }
        
        XCTAssertEqual(results[0], false)
        XCTAssertEqual(results[1], true)
        XCTAssertEqual(results[2], false)
    }
}
