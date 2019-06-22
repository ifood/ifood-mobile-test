//
//  TweetListWorker.swift
//  TwitterAnalyzer
//
//  Created by Bruno Vieira on 18/06/19.
//  Copyright © 2019 Bruno Vieira. All rights reserved.
//

import UIKit

class TweetListWorker {    
    func requestUserProfileImage(from stringUrl: String,
                                 success: @escaping ((Data?) -> Void),
                                 failure: @escaping ((ApplicationError) -> Void)) {
        TwitterAnalyzerService().getUserProfileImage(from: stringUrl, success: success) { (networkError) in
            failure(ApplicationError(.userInfoUnavailable))
        }
    }
    
    func analyzeTweet(text: String,
                      success: @escaping ((SentimentAnalysis) -> Void),
                      failure: @escaping ((ApplicationError) -> Void)) {
        TwitterAnalyzerService().getTweetAnalysis(text: text, success: success) { (networkError) in
            failure(ApplicationError(.textAnalysisUnavailable))
        }
    }
}
