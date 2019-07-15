//
//  TweetMoodViewModelTests.swift
//  twitter-moodTests
//
//  Created by Dennis Merli on 7/11/19.
//  Copyright © 2019 Dennis Merli. All rights reserved.
//

import Foundation
import XCTest

@testable import twitter_mood

class TweetMoodViewModelTests: XCTestCase {
    
    func testLoadHappyTweetMood() {
       let data = MoodMock.loadMoodMockHappy()
        do {
            let tweetMood = try JSONDecoder().decode(TweetMood.self, from: data)
            let moodViewModel = TweetMoodViewModel(tweetMood: tweetMood)
            
            XCTAssertEqual(moodViewModel.moodState, TweetMoodViewModel.MoodState.happy)
            XCTAssertEqual(moodViewModel.emojiForMood, "😃")
            
        } catch {
            XCTFail("error: \(error.localizedDescription)")
        }
    }
    
    func testLoadNeutralTweetMood() {
        let data = MoodMock.loadMoodMockNeutral()
        do {
            let tweetMood = try JSONDecoder().decode(TweetMood.self, from: data)
            let moodViewModel = TweetMoodViewModel(tweetMood: tweetMood)
            
            XCTAssertEqual(moodViewModel.moodState, TweetMoodViewModel.MoodState.neutral)
            XCTAssertEqual(moodViewModel.emojiForMood, "😐")
            
        } catch {
            XCTFail("error: \(error.localizedDescription)")
        }
    }
}

