//
//  TweetFactory.swift
//  SentimentAnalysisTests
//
//  Created by Thales Frigo on 03/12/18.
//  Copyright © 2018 Thales Frigo. All rights reserved.
//

import Foundation
@testable import SentimentAnalysis

struct TweetFactory {
    static func makeTweet() -> Tweet {
        return Tweet(
            createdAt: Date(),
            text: "Don’t do it max you seen what happen to Connor when he had him ring side 🤦🏽‍♂️ 😬 but I guess you fighting in Canada",
            user: User(
                name: "TheeJCaldy760",
                screenName: "TheeJCaldy760",
                image: URL(string: "https://pbs.twimg.com/profile_images/948572346297745408/VHEgZWHA_400x400.jpg")!
            )
        )
    }
}
