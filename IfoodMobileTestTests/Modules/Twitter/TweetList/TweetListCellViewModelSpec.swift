//
//  TweetListCellViewModelSpec.swift
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

class TweetListCellViewModelSpec: QuickSpec {
    
    var viewModel: TweetListCellViewModel!
    let tweet = TweetModel(text: "Today's new update means that you can finally add Pizza",
                           id: 1125490788736032770,
                           createdAt: "Mon May 06 20:01:29 +0000 2019",
                           user: TwitterUser(id: "2244994945",
                                             name: "Twitter Dev",
                                             screenName: "TwitterDev",
                                             profileImageURL: URL(string: "https://pbs.twimg.com/profile_images/880136122604507136/xHrnqf1T_normal.jpg")))
    
    override func spec() {
        describe("TweetListCellViewModelSpec") {
            beforeEach {
                self.viewModel = TweetListCellViewModel(tweet: self.tweet)
            }
            
            it("tweetText", closure: {
                expect(self.viewModel.tweetText).to(equal(self.tweet.text))
            })
            
            it("imgProfile", closure: {
                expect(self.viewModel.imgProfile?.absoluteString).to(equal(self.tweet.user?.profileImageURL?.absoluteString))
            })
            
            it("tweetDate", closure: {
                expect(self.viewModel.tweetDate).to(equal("06/05/2019 17:01"))
            })
        }
    }
}
