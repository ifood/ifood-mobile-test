//
//  TweetAnalyzeViewModelSpec.swift
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

class TweetAnalyzeViewModelSpec: QuickSpec {
    
    var service: TweetAnalyzeServiceMock!
    var viewModel: TweetAnalyzeViewModel!
    
    override func spec() {
        describe("TweetAnalyzeViewModelSpec") {
            context("natural tweet", closure: {
                beforeEach {
                    self.service = TweetAnalyzeServiceMock()
                    self.viewModel = TweetAnalyzeViewModel(tweet: "", service: self.service)
                }
                
                it("bgColor to be gray", closure: {
                    expect(try? self.viewModel.bgColor.value()).to(equal(.gray))
                })
                
                it("emoji to be natural", closure: {
                    expect(try? self.viewModel.emoji.value()).to(equal("😐"))
                })
                
                it("errorMessage to be nil", closure: {
                    expect(try? self.viewModel.errorMessage.value()).to(beNil())
                })
                
                it("showLoader to be false", closure: {
                    expect(try? self.viewModel.showLoader.value()).to(beFalse())
                })
            })
            
            context("Happy tweet", closure: {
                beforeEach {
                    self.service = TweetAnalyzeServiceMock()
                    self.service.score = 0.9
                    self.viewModel = TweetAnalyzeViewModel(tweet: "", service: self.service)
                }

                it("bgColor to be yellow", closure: {
                    expect(try? self.viewModel.bgColor.value()).to(equal(.yellow))
                })
                
                it("emoji to be happy", closure: {
                    expect(try? self.viewModel.emoji.value()).to(equal("😃"))
                })
                
                it("errorMessage to be nil", closure: {
                    expect(try? self.viewModel.errorMessage.value()).to(beNil())
                })
                
                it("showLoader to be false", closure: {
                    expect(try? self.viewModel.showLoader.value()).to(beFalse())
                })
            })
            
            context("sad tweet", closure: {
                beforeEach {
                    self.service = TweetAnalyzeServiceMock()
                    self.service.score = -0.9
                    self.viewModel = TweetAnalyzeViewModel(tweet: "", service: self.service)
                }
                
                it("bgColor to be blue", closure: {
                    expect(try? self.viewModel.bgColor.value()).to(equal(.blue))
                })
                
                it("emoji to be sad", closure: {
                    expect(try? self.viewModel.emoji.value()).to(equal("😔"))
                })
                
                it("errorMessage to be nil", closure: {
                    expect(try? self.viewModel.errorMessage.value()).to(beNil())
                })
                
                it("showLoader to be false", closure: {
                    expect(try? self.viewModel.showLoader.value()).to(beFalse())
                })
            })
            
            context("with Error", closure: {
                beforeEach {
                    self.service = TweetAnalyzeServiceMock()
                    self.service.error = true
                    self.viewModel = TweetAnalyzeViewModel(tweet: "", service: self.service)
                }
                
                it("bgColor to be blue", closure: {
                    expect(try? self.viewModel.bgColor.value()).to(equal(.white))
                })
                
                it("emoji to be sad", closure: {
                    expect(try? self.viewModel.emoji.value()).to(equal(""))
                })
                
                it("errorMessage to be nil", closure: {
                    expect(try? self.viewModel.errorMessage.value()).to(equal("Sorry an error occurred. \n Please try again"))
                })
                
                it("showLoader to be false", closure: {
                    expect(try? self.viewModel.showLoader.value()).to(beFalse())
                })
            })
        }
    }
}
