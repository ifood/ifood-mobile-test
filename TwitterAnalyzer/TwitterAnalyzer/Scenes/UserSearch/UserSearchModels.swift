//
//  UserSearchModels.swift
//  TwitterAnalyzer
//
//  Created by Bruno Vieira on 18/06/19.
//  Copyright © 2019 Bruno Vieira. All rights reserved.
//

import UIKit

enum UserSearch {
    // MARK: Use cases
    enum AuthenticateTwitter{
        struct Request {}
        struct Response {}
        struct ViewModel {}
    }
    
    enum SearchUser {
        struct Request {
            let userAccount: String
        }
        struct Response {
            let user: User
        }
        struct ViewModel {
            
        }
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
