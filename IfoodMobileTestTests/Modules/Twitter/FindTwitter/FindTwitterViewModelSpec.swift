//
//  FindTwitterViewModelSpec.swift
//  IfoodMobileTestTests
//
//  Created by André Vieira on 10/06/19.
//  Copyright © 2019 André Vieira. All rights reserved.
//

import Foundation
import Quick
import Nimble
import RxSwift

@testable import IfoodMobileTest

class FindTwitterViewModelSpec: QuickSpec {
    
    var service: FindTwitterServiceMock!
    var viewModel: FindTwitterViewModel!
    private let bag = DisposeBag()
    
    override func spec() {
        describe("FindTwitterViewModelSpec") {
            context("without error", closure: {
                beforeEach {
                    self.service = FindTwitterServiceMock()
                    self.viewModel = FindTwitterViewModel(service: self.service)
                    _ = Observable.just("iFood - movile pay").bind(to: self.viewModel.userName)
                    self.viewModel.findTweets()
                }
                
                it("isValidUser", closure: {
                    expect(try? self.viewModel.isValidUser.value()).to(beTrue())
                })
                
                it("tweets", closure: {
                    expect(try? self.viewModel.tweets.value().count).to(equal(1))
                })
                
                it("errorMessage", closure: {
                    expect(try? self.viewModel.errorMessage.value()).to(beNil())
                })
                
                it("showLoader", closure: {
                    expect(try? self.viewModel.showLoader.value()).to(beFalse())
                })
            })
            
            context("Without internet", closure: {
                beforeEach {
                    self.service = FindTwitterServiceMock()
                    self.service.errorWithoutInternet = true
                    self.viewModel = FindTwitterViewModel(service: self.service)
                    _ = Observable.just("iFood - movile pay").bind(to: self.viewModel.userName)
                    self.viewModel.findTweets()
                }
                
                it("isValidUser", closure: {
                    expect(try? self.viewModel.isValidUser.value()).to(beTrue())
                })
                
                it("tweets", closure: {
                    expect(try? self.viewModel.tweets.value().count).to(equal(0))
                })
                
                it("errorMessage", closure: {
                    expect(try? self.viewModel.errorMessage.value()).to(equal("You are offline or with problem in your internet"))
                })
                
                it("showLoader", closure: {
                    expect(try? self.viewModel.showLoader.value()).to(beFalse())
                })
            })
            
            context("twettir not found", closure: {
                beforeEach {
                    self.service = FindTwitterServiceMock()
                    self.service.errorTwitterNotFound = true
                    self.viewModel = FindTwitterViewModel(service: self.service)
                    _ = Observable.just("iFood - movile pay").bind(to: self.viewModel.userName)
                    self.viewModel.findTweets()
                }
                
                it("isValidUser", closure: {
                    expect(try? self.viewModel.isValidUser.value()).to(beTrue())
                })
                
                it("tweets", closure: {
                    expect(try? self.viewModel.tweets.value().count).to(equal(0))
                })
                
                it("errorMessage", closure: {
                    expect(try? self.viewModel.errorMessage.value()).to(equal("We couldn't find this user. \n Please, try another username."))
                })
                
                it("showLoader", closure: {
                    expect(try? self.viewModel.showLoader.value()).to(beFalse())
                })
            })
            
            context("twettir not found", closure: {
                beforeEach {
                    self.service = FindTwitterServiceMock()
                    self.service.errorGeneric = true
                    self.viewModel = FindTwitterViewModel(service: self.service)
                    _ = Observable.just("iFood - movile pay").bind(to: self.viewModel.userName)
                    self.viewModel.findTweets()
                }
                
                it("isValidUser", closure: {
                    expect(try? self.viewModel.isValidUser.value()).to(beTrue())
                })
                
                it("tweets", closure: {
                    expect(try? self.viewModel.tweets.value().count).to(equal(0))
                })
                
                it("errorMessage", closure: {
                    expect(try? self.viewModel.errorMessage.value()).to(equal("Sorry an error occurred. \n Please try again"))
                })
                
                it("showLoader", closure: {
                    expect(try? self.viewModel.showLoader.value()).to(beFalse())
                })
            })
        }
    }
}


/*
 var tweets: BehaviorSubject<[TweetModel]> { get }
 var errorMessage: BehaviorSubject<String?> { get }
 var showLoader: BehaviorSubject<Bool> { get }
 */
