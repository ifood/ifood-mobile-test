//
//  TwitterAnalyzerService.swift
//  TwitterAnalyzer
//
//  Created by Bruno Vieira on 19/06/19.
//  Copyright © 2019 Bruno Vieira. All rights reserved.
//

import Foundation

protocol TwitterAnalyzerServiceProtocol {
    func authenticateTwitter(success: @escaping (() -> Void),
                             failure: @escaping ((NetworkError) -> Void))
    func getTwitterAccountInformation(user: String,
                                      success: @escaping ((User) -> Void),
                                      failure: @escaping ((NetworkError) -> Void))
    func getTweetList(user: String,
                      success: @escaping (([Tweet]) -> Void),
                      failure: @escaping ((NetworkError) -> Void))
    func getUserProfileImage(from: String,
                             success: @escaping ((Data?) -> Void),
                             failure: @escaping ((NetworkError) -> Void))
    func getTweetAnalysis(text: String,
                          success: @escaping ((SentimentAnalysis) -> Void),
                          failure: @escaping ((NetworkError) -> Void))
}

class TwitterAnalyzerService: TwitterAnalyzerServiceProtocol {
    
    // MARK: - Properties
    
    private(set) var dispatcher: NetworkDispatcherProtocol
    private(set) var session: SessionProtocol
    
    // MARK: - Initializers
    
    init() {
        dispatcher = NetworkDispatcher()
        session = Session.shared
    }
    
    init(dispatcher: NetworkDispatcherProtocol, session: SessionProtocol) {
        self.dispatcher = dispatcher
        self.session = session
    }
    
    // MARK: - Services
    
    func authenticateTwitter(success: @escaping (() -> Void),
                             failure: @escaping ((NetworkError) -> Void)) {
        let request = TwitterAuthenticationRequest(twitterApiKey: Environment.shared.twitterApiToken, twitterApiSecretKey: Environment.shared.twitterSecretApiToken)
        dispatcher.requestObject(of: TwitterAuthentication.self, request: request) { (twitterAuthentication, error) in
            if let error = error {
                failure(error)
                return
            } else {
                self.session.twitterAuthenticatedToken = twitterAuthentication?.accessToken
                success()
            }
        }
    }

    func getTwitterAccountInformation(user: String,
                                      success: @escaping ((User) -> Void),
                                      failure: @escaping ((NetworkError) -> Void)) {
        guard let accessToken = session.twitterAuthenticatedToken else {
            failure(NetworkError(.invalidRequest))
            return
        }
        let request = TwitterAccountRequest(accessToken: accessToken, screenName: user)
        dispatcher.requestObject(of: User.self, request: request) { (userResponse, error) in
            if let error = error {
                failure(error)
                return
            } else {
                guard let userResponse = userResponse else {
                    failure(NetworkError(.invalidResponse))
                    return
                }
                success(userResponse)
            }
        }
    }
    
    func getTweetList(user: String,
                      success: @escaping (([Tweet]) -> Void),
                      failure: @escaping ((NetworkError) -> Void)) {
        guard let accessToken = session.twitterAuthenticatedToken else {
            failure(NetworkError(.invalidRequest))
            return
        }
        let request = TweetListRequest(accessToken: accessToken, screenName: user)
        dispatcher.requestArray(of: Tweet.self, request: request) { (tweets, error) in
            if let error = error {
                failure(error)
                return
            } else {
                guard let tweets = tweets else {
                    failure(NetworkError(.invalidResponse))
                    return
                }
                success(tweets)
            }
        }
    }
    
    func getUserProfileImage(from: String,
                             success: @escaping ((Data?) -> Void),
                             failure: @escaping ((NetworkError) -> Void)) {
        dispatcher.requestImage(from: from) { (imageData, error) in
            if let error = error {
                failure(error)
                return
            } else {
                guard let data = imageData else {
                    failure(NetworkError(.invalidResponse))
                    return
                }
                success(data)
            }
        }
    }
    
    func getTweetAnalysis(text: String,
                          success: @escaping ((SentimentAnalysis) -> Void),
                          failure: @escaping ((NetworkError) -> Void)) {
        let request = AnalyzeTweetRequest(text: text, googleApiKey: Environment.shared.googleApiToken)
        dispatcher.requestObject(of: SentimentAnalysis.self, request: request) { (sentimentAnalysis, error) in
            if let error = error {
                failure(error)
                return
            } else {
                guard let analysis = sentimentAnalysis else {
                    failure(NetworkError(.invalidResponse))
                    return
                }
                success(analysis)
            }
        }
    }
}
