//
//  TwitterAnalyzerServiceStub.swift
//  TwitterAnalyzerTests
//
//  Created by Bruno Vieira on 21/06/19.
//  Copyright © 2019 Bruno Vieira. All rights reserved.
//

import Foundation
@testable import TwitterAnalyzer

class TwitterAnalyzerServiceStub: TwitterAnalyzerServiceProtocol {
    
    enum MockType {
        case authenticateTwitterSuccess
        case authenticateTwitterFailure
        case getTwitterAccountInformationSuccess
        case getTwitterAccountInformationFailure
        case getTwitterAccountInformationFailureNotFound
        case getTweetListSuccess
        case getTweetListFailure
        case getTweetListFailureNotFound
        case getUserProfileImageSuccess
        case getUserProfileImageFailure
        case getTweetAnalysisSuccess
        case getTweetAnalysisFailure
    }
    
    var mockType: MockType
    
    init(type: MockType) {
        mockType = type
    }
    
    func authenticateTwitter(success: @escaping (() -> Void), failure: @escaping ((NetworkError) -> Void)) {
        switch mockType {
        case .authenticateTwitterSuccess:
            success()
            break
        case .authenticateTwitterFailure:
            failure(NetworkError(.invalidRequest))
            break
        default:
            break
        }
    }
    
    func getTwitterAccountInformation(user: String, success: @escaping ((User) -> Void), failure: @escaping ((NetworkError) -> Void)) {
        switch mockType {
        case .getTwitterAccountInformationSuccess:
            success(User.mockedUser())
            break
        case .getTwitterAccountInformationFailure:
            failure(NetworkError(.unknown))
            break
        case .getTwitterAccountInformationFailureNotFound:
            failure(NetworkError(.urlNotFound))
            break
        default:
            break
        }
    }
    
    func getTweetList(user: String, success: @escaping (([Tweet]) -> Void), failure: @escaping ((NetworkError) -> Void)) {
        switch mockType {
        case .getTweetListSuccess:
            success(Tweet.mockedTweetList())
            break
        case .getTweetListFailure:
            failure(NetworkError(.unknown))
            break
        case .getTweetListFailureNotFound:
            failure(NetworkError(.urlNotFound))
        default:
            break
        }
    }
    
    func getUserProfileImage(from: String, success: @escaping ((Data?) -> Void), failure: @escaping ((NetworkError) -> Void)) {
        switch mockType {
        case .getUserProfileImageSuccess:
            break
        case .getUserProfileImageFailure:
            break
        default:
            break
        }
    }
    
    func getTweetAnalysis(text: String, success: @escaping ((SentimentAnalysis) -> Void), failure: @escaping ((NetworkError) -> Void)) {
        switch mockType {
        case .getTweetAnalysisSuccess:
            break
        case .getTweetAnalysisFailure:
            break
        default:
            break
        }
    }
    
}
