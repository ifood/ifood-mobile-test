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
                
                it("isValidUser to be valid", closure: {
                    expect(try? self.viewModel.isValidUser.value()).to(beTrue())
                })
                
                it("tweets to be fill an register", closure: {
                    expect(try? self.viewModel.tweets.value().count).to(equal(1))
                })
                
                it("errorMessage to be nil", closure: {
                    expect(try? self.viewModel.errorMessage.value()).to(beNil())
                })
                
                it("showLoader to be false", closure: {
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
                
                it("isValidUser to be valid", closure: {
                    expect(try? self.viewModel.isValidUser.value()).to(beTrue())
                })
                
                it("tweets to be not fill", closure: {
                    expect(try? self.viewModel.tweets.value().count).to(equal(0))
                })
                
                it("errorMessage to be fill with not internet msg", closure: {
                    expect(try? self.viewModel.errorMessage.value()).to(equal("You are offline or with problem in your internet"))
                })
                
                it("showLoader to be false", closure: {
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
                
                it("isValidUser to be valid", closure: {
                    expect(try? self.viewModel.isValidUser.value()).to(beTrue())
                })
                
                it("tweets to be not fill", closure: {
                    expect(try? self.viewModel.tweets.value().count).to(equal(0))
                })
                
                it("errorMessage to be fill with not found msg", closure: {
                    expect(try? self.viewModel.errorMessage.value()).to(equal("We couldn't find this user. \n Please, try another username."))
                })
                
                it("showLoader to be false", closure: {
                    expect(try? self.viewModel.showLoader.value()).to(beFalse())
                })
            })
            
            context("twettir Generic Error", closure: {
                beforeEach {
                    self.service = FindTwitterServiceMock()
                    self.service.errorGeneric = true
                    self.viewModel = FindTwitterViewModel(service: self.service)
                    _ = Observable.just("iFood - movile pay").bind(to: self.viewModel.userName)
                    self.viewModel.findTweets()
                }
                
                it("isValidUser to be valid", closure: {
                    expect(try? self.viewModel.isValidUser.value()).to(beTrue())
                })
                
                it("tweets to be not fill", closure: {
                    expect(try? self.viewModel.tweets.value().count).to(equal(0))
                })
                
                it("errorMessage to be fill with generic msg", closure: {
                    expect(try? self.viewModel.errorMessage.value()).to(equal("Sorry an error occurred. \n Please try again"))
                })
                
                it("showLoader to be false", closure: {
                    expect(try? self.viewModel.showLoader.value()).to(beFalse())
                })
            })
        }
    }
}
