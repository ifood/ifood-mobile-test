//
//  HomeViewModelTests.swift
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

class HomeViewModelTests: QuickSpec {
    
    override func spec() {
        super.spec()
        
        var disposeBag: DisposeBag!
        var subject: HomeViewModelType!
        var vmDelegate: HomeViewModelDelegateMock?
        let twitterDataSource = TwitterDataSourceMock()
        
        
        beforeEach {
            disposeBag = DisposeBag()
            let vm = HomeViewModel(twitterDataSource: twitterDataSource)
            vmDelegate = HomeViewModelDelegateMock()
            vm.delegate = vmDelegate
            subject = vm
        }
        
        afterEach {
            subject = nil
            disposeBag = nil
            vmDelegate = nil
        }
        
        describe("when user wants to list tweets") {
            context("and did fill username") {
                
                it("should load twitter user account for a valid username") {
                    let user = TwitterUser(idStr: "123",
                                           name: "Testman",
                                           screenName: "testman",
                                           profileImageUrlHttps: "http://google.com")
                    twitterDataSource.getUserData = Observable.just(user)
                    
                    subject.username.value = user.screenName
                    subject.listTweets.onNext(())
                    
                    expect(vmDelegate?.userLoaded?.idStr) == user.idStr
                    expect(vmDelegate?.userLoaded?.name) == user.name
                    expect(vmDelegate?.userLoaded?.screenName) == user.screenName
                }
                
                it("should show error message for a invalid username") {
                    
                    let scheduler = TestScheduler(initialClock: 0)
                    let observer = scheduler.createObserver(Void.self)
                    
                    twitterDataSource.getUserData = Observable.error(TwitterError.couldNotLoadUser)
                    
                    subject.username.value = "testman"
                    subject.couldNotLoadUser
                        .subscribe(observer)
                        .disposed(by: disposeBag)
                    subject.listTweets.onNext(())
                    
                    scheduler.start()
                    
                    expect(observer.events.count) == 1
                }
                
                it("should show loading while request user data") {
                    
                    let scheduler = TestScheduler(initialClock: 0)
                    let observer = scheduler.createObserver(Bool.self)
                    
                    subject.isLoadingUser
                        .subscribe(observer)
                        .disposed(by: disposeBag)
                    
                    subject.listTweets.onNext(())
                    
                    scheduler.start()
                    
                    // initial loading hidden
                    // show while requesting tweets
                    // hide when finish load
                    let result = observer.events.map { $0.value.element! }
                    expect(result) == [false, true, false]
                }
            }
        }
        
        
    }
    
}


class HomeViewModelDelegateMock: HomeViewModelDelegate {
    var userLoaded: TwitterUser? = nil
    func didLoadUser(user: TwitterUser) {
        userLoaded = user
    }
}
