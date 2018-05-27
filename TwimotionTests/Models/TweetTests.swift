//
//  TweetTests.swift
//  TwimotionTests
//
//  Created by Antony on 27/05/18.
//  Copyright © 2018 Twimotion. All rights reserved.
//

import XCTest
@testable import Twimotion

class TweetTests: XCTestCase {
    
    func testJSONMapping() {
        let jsonData = stub("user_timeline")!
        let tweets = try! JSONDecoder.sharedDecoder.decode([Tweet].self, from: jsonData)

        XCTAssertEqual(tweets.count, 20)
        
        let firstTweet = tweets.first!
        
        XCTAssertEqual(firstTweet.idStr, "997318367529848832")
        XCTAssertEqual(firstTweet.text, "Supercool tool for git users🔧. Why didn't I find this before??? https://t.co/8VtBIjnllu #git #github")
        XCTAssertEqual(firstTweet.user.idStr, "147239582")
        XCTAssertEqual(firstTweet.user.screenName, "antonyalkmim")
        
    }
    
}
