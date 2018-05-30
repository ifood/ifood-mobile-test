//
//  TweetItemViewModel.swift
//  TwimotionTests
//
//  Created by Antony on 30/05/18.
//  Copyright © 2018 Twimotion. All rights reserved.
//

import XCTest
import Nimble
import Quick
import RxSwift

@testable import Twimotion

class TweetItemViewModelTests: QuickSpec {
    
    override func spec() {
        super.spec()
        
        var subject: TweetItemViewModelType!
        let twitterDataSource = TwitterDataSourceMock()
        
        beforeEach {
            subject = TweetItemViewModel(tweet: twitterDataSource.tweetMock)
        }
        
        afterEach {
            subject = nil
        }
        
        it("should show tweet data formatted") {
            expect(subject.username) == "@\(twitterDataSource.userMock.screenName)"
            expect(subject.name) == twitterDataSource.userMock.name
            expect(subject.text) == twitterDataSource.tweetMock.text
            
            expect(subject.profileImageUrl?.absoluteString) == twitterDataSource.tweetMock.user.profileImageUrlHttps
            
            let fmt = DateFormatter(format: "dd/MM/yyyy")
            let dateFormatted = fmt.string(from: twitterDataSource.tweetMock.createdAt)
            expect(subject.formattedDate) == dateFormatted
            
        }
        
    }
}
