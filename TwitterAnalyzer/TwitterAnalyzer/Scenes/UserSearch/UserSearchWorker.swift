//
//  UserSearchWorker.swift
//  TwitterAnalyzer
//
//  Created by Bruno Vieira on 18/06/19.
//  Copyright © 2019 Bruno Vieira. All rights reserved.
//

import UIKit

protocol UserSearchWorkerProtocol {
    func authenticateTwitter(success: @escaping (() -> Void),
                             failure: @escaping ((ApplicationError) -> Void))
    func searchUser(account: String,
                    success: @escaping ((User) -> Void),
                    failure: @escaping ((ApplicationError) -> Void))    
}

class UserSearchWorker: UserSearchWorkerProtocol {
    private let service: TwitterAnalyzerServiceProtocol
    
    init(service: TwitterAnalyzerServiceProtocol) {
        self.service = service
    }
    
    func authenticateTwitter(success: @escaping (() -> Void),
                             failure: @escaping ((ApplicationError) -> Void)) {
        service.authenticateTwitter(success: success) { (networkError) in
            failure(ApplicationError(.unknown))
        }
    }
    
    func searchUser(account: String,
                    success: @escaping ((User) -> Void),
                    failure: @escaping ((ApplicationError) -> Void)) {
        var user: User?
        var userErrorMessage: ApplicationError?
        var tweets: [Tweet]?
        var tweetsErrorMessage: ApplicationError?
        
        let group = DispatchGroup()
        group.enter()
        service.getTwitterAccountInformation(user: account, success: { (responseUser) in
            user = responseUser
            group.leave()
        }) { (networkError) in
            userErrorMessage = ApplicationError(networkError.code == .urlNotFound ? .userNotFound : .userInfoUnavailable)
            group.leave()
        }
        group.enter()
        service.getTweetList(user: account, success: { (responseTweets) in
            tweets = responseTweets
            group.leave()
        }) { (networkError) in
            tweetsErrorMessage = ApplicationError(networkError.code == .urlNotFound ? .userNotFound : .userInfoUnavailable)
            group.leave()
        }
        group.notify(queue: .main) {
            if let userErrorMessage = userErrorMessage {
                failure(userErrorMessage)
                return
            }
            if let tweetsErrorMessage = tweetsErrorMessage {
                failure(tweetsErrorMessage)
                return
            }
            guard var user = user, let tweets = tweets else {
                failure(ApplicationError(.userInfoUnavailable))
                return
            }
            user.tweets = tweets
            success(user)
        }
    }
}
