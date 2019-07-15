//
//  TweetDetailLocalizable.swift
//  twitter-mood
//
//  Created by Dennis Merli on 7/11/19.
//  Copyright © 2019 Dennis Merli. All rights reserved.
//

import Foundation

final class TweetDetailLocalizable: BaseLocalizable {
    
    static let tweetMood: String = NSLocalizedString("Tweet Mood",
                                                       tableName: "BaseLocalizable",
                                                       bundle: Bundle.main,
                                                       value: "Tweet Mood",
                                                       comment: "Tweet Mood title")
    
    static let tweetDetail: String = NSLocalizedString("Tweet Detail",
                                                     tableName: "BaseLocalizable",
                                                     bundle: Bundle.main,
                                                     value: "Tweet Detail",
                                                     comment: "Tweet Detail title")
    
}
