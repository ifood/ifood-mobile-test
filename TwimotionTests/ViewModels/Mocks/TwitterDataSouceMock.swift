//
//  TwitterDataSouceMock.swift
//  TwimotionTests
//
//  Created by Antony on 30/05/18.
//  Copyright © 2018 Twimotion. All rights reserved.
//

import Foundation
import RxSwift

@testable import Twimotion

class TwitterDataSourceMock: TwitterDataSourceType {
    
    let userMock = TwitterUser(
        idStr: "147239582",
        name: "Antony Alkmim",
        screenName: "antonyalkmim",
        profileImageUrlHttps: "https://pbs.twimg.com/profile_images/822536611657371648/ATH3P28e_normal.jpg"
    )
    
    var tweetMock: Tweet {
        return Tweet(
            idStr: "1234",
            text: "Happy tweet!",
            user: userMock,
            createdAt: Date()
        )
    }
    
    lazy var getUserData: Observable<TwitterUser> = {
        return Observable.just(userMock)
    }()
    
    lazy var getLastestTweetsData: Observable<[Tweet]> = {
        let jsonData = stub("user_timeline")!
        let tweets = try! JSONDecoder.sharedDecoder.decode([Tweet].self, from: jsonData)
        return Observable.just(tweets)
    }()
    
    func getUser(username: String) -> Observable<TwitterUser> {
        return getUserData
    }
    
    func getLatestTweets(from user: TwitterUser) -> Observable<[Tweet]> {
        return getLastestTweetsData
    }
}
