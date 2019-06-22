//
//  TweetListModels.swift
//  TwitterAnalyzer
//
//  Created by Bruno Vieira on 18/06/19.
//  Copyright © 2019 Bruno Vieira. All rights reserved.
//

import UIKit

enum TweetList {
    // MARK: Use cases
    
    enum LoadInformation {
        struct Request {}
        struct Response {
            let user: User
            let data: Data?
        }
        struct ViewModel {
            struct Tweet {
                let text: String
                let date: String
                let detail: String?
            }
            
            let user: String
            let name: String
            var profileImage: UIImage?
            let tweets: [Tweet]
        }
    }
    
    enum SelectTweet {
        struct Request {
            let row: Int
        }
        struct Response {
            let tweet: Tweet
        }
        struct ViewModel {}
    }
    
    enum Error {
        struct Request {}
        struct Response {
            let error: ApplicationError
        }
        struct ViewModel {
            let errorTitle: String
            let errorMessage: String
        }
    }
}
