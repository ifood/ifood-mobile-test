//
//  MockedUser.swift
//  TwitterAnalyzerTests
//
//  Created by Bruno Vieira on 21/06/19.
//  Copyright © 2019 Bruno Vieira. All rights reserved.
//

import Foundation
@testable import TwitterAnalyzer

extension User {
    static func mockedUser() -> User {
        let user = User(name: "Mocked Name",
                        account: "mockedAccount",
                        profilePictureUrl: "https://via.placeholder.com/50",
                        tweets: Tweet.mockedTweetList())
        return user
    }
    
    
}
