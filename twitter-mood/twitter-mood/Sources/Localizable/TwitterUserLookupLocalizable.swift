//
//  TwitterUserLookupLocalizable.swift
//  twitter-mood
//
//  Created by Dennis Merli on 7/7/19.
//  Copyright © 2019 Dennis Merli. All rights reserved.
//

import Foundation

final class TwitterUserLookupLocalizable: BaseLocalizable {
    
    static let twitterUser: String = NSLocalizedString("Twitter User",
                                                   tableName: "BaseLocalizable",
                                                   bundle: Bundle.main,
                                                   value: "Twitter User",
                                                   comment: "Twitter User title")
    
    static let seeTweets: String = NSLocalizedString("See Tweets",
                                                tableName: "BaseLocalizable",
                                                bundle: Bundle.main,
                                                value: "See Tweets",
                                                comment: "See Tweets title")
    
    static let twitterUserName: String = NSLocalizedString("Enter a Twitter username",
                                                     tableName: "BaseLocalizable",
                                                     bundle: Bundle.main,
                                                     value: "Enter a Twitter username",
                                                     comment: "Enter a Twitter username title")
}
