//
//  TweetListViewModel.swift
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

class TweetsListViewModelTests: QuickSpec {
    
    override func spec() {
        super.spec()
        
        var disposeBag: DisposeBag!
        var subject: TweetsListViewModelType!
        var vmDelegate: TweetsListViewModelDelegateMock?
        var twitterDataSource: TwitterDataSourceMock!
        
        beforeEach {
            disposeBag = DisposeBag()
            twitterDataSource = TwitterDataSourceMock()
            let vm = TweetsListViewModel(twitterUser: twitterDataSource.userMock  ,
                                     twitterDataSource: twitterDataSource)
            vmDelegate = TweetsListViewModelDelegateMock()
            vm.delegate = vmDelegate
            subject = vm
        }
        
        afterEach {
            subject = nil
            disposeBag = nil
            vmDelegate = nil
        }
        
        it("should show twitter username account") {
            let username = try! subject.username.toBlocking().first()!
            expect(username) == "antonyalkmim"
        }
        
        it("should load lastest twenty tweets from user") {
            subject.loadTweets.onNext(())
            let result = try! subject.cellViewModels.toBlocking().first()!
            expect(result.first?.items.count) == 20
        }
        
        it("should show loading while request lastest tweets") {
            let scheduler = TestScheduler(initialClock: 0)
            let observer = scheduler.createObserver(Bool.self)

            subject.isLoadingTweets
                .subscribe(observer)
                .disposed(by: disposeBag)
            
            subject.loadTweets.onNext(())
            
            scheduler.start()
            
            // initial loading hidden
            // show while requesting tweets
            // hide when finish load
            let result = observer.events.map { $0.value.element! }
            expect(result) == [false, true, false]
        }
        
        it("should show error message when could not load tweets") {
            twitterDataSource.getLastestTweetsData = Observable.error(TwitterError.couldNotLoadTweets)
            
            let scheduler = TestScheduler(initialClock: 0)
            let observerCouldNotLoadTweets = scheduler.createObserver(Bool.self)
            let observerLoading = scheduler.createObserver(Bool.self)
            
            subject.couldNotLoadTweets.subscribe(observerCouldNotLoadTweets).disposed(by: disposeBag)
            subject.isLoadingTweets.subscribe(observerLoading).disposed(by: disposeBag)
            
            subject.loadTweets.onNext(())
            
            scheduler.start()
            
            // test error
            let resultErrorObserver = observerCouldNotLoadTweets.events.map { $0.value.element! }
            expect(resultErrorObserver) == [false, false, true]
            
            // test loading
            let resultLoading = observerLoading.events.map { $0.value.element! }
            expect(resultLoading) == [false, true, false]
        }
        
        it("should notify delegate when item selected") {
            subject.loadTweets.onNext(())
            
            let result = try! subject.cellViewModels.toBlocking().first()!
            expect(result.first?.items.count) == 20
            
            subject.selectTweetEvent.onNext(0)
            
            expect(vmDelegate?.tweet).toNot(beNil())
            expect(vmDelegate?.tweet?.idStr) == "997318367529848832"
        }
        
    }
    
    
}

class TweetsListViewModelDelegateMock: TweetsListViewModelDelegate {
    var tweet: Tweet? = nil
    
    func didSelectTweet(_ tweet: Tweet) {
        self.tweet = tweet
    }
}


