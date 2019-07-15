//
//  TwitterDetailViewModelTests.swift
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

class TwitterDetailViewModelTests: XCTestCase {
    
    var viewModel: TweetDetailViewModel!
    var disposeBag: DisposeBag!
    
    override func setUp() {
        super.setUp()
        disposeBag = DisposeBag()
        let tweetMocks = TweetMock.loadTweetMock()
        let userTweets = try? JSONDecoder().decode([Tweet].self, from: tweetMocks)
        if let cellViewModelMocks = userTweets.map({ $0.map({ UserTweetCellViewModel(tweet: $0)}) }) {
            viewModel = TweetDetailViewModel(tweetCellViewModel: cellViewModelMocks.first!, googleService: GoogleService(operationMode: .stubResponse))
        }
    }
    
    override func tearDown() {
        super.tearDown()
        viewModel = nil
    }
    
    func testLoadTweetMood() {
        let scheduler = TestScheduler(initialClock: 0)
        let observer = scheduler.createObserver(TweetMoodViewModel?.self)
        
        scheduler.scheduleAt(100) {
            self.viewModel.tweetMood.asObservable()
                .subscribe(observer)
                .disposed(by: self.disposeBag)
        }
        
        scheduler.scheduleAt(200) {
            self.viewModel.loadMoodFromTweet()
        }
        
        scheduler.start()
        let results = observer.events.map { event in
            (event.value.element)!
        }
        
        XCTAssertNil(results[0])
        XCTAssertNotNil(results[1])
    }
}
