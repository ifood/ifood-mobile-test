//
//  GoogleProvider.swift
//  twitter-mood
//
//  Created by Dennis Merli on 7/7/19.
//  Copyright © 2019 Dennis Merli. All rights reserved.
//

import Moya

enum GoogleProvider {
    case moodForTweet(_ tweet: String)
}

extension GoogleProvider: TargetType {
    var baseURL: URL {
        return NetworkManager.shared.currentEnvVars?.apiVariablesGoogle?.baseURL! ?? URL(string: "")!
    }
    
    var path: String {
        switch self {
        case .moodForTweet(tweet: _):
            return "/documents:analyzeSentiment"
        }
    }
    
    var method: Moya.Method {
        switch self {
        case .moodForTweet(tweet: _):
            return .post
        }
    }
    
    var sampleData: Data {
        switch self {
        case .moodForTweet(tweet: _):
            return MoodMock.loadMoodMockHappy()
        }
    }
    
    var task: Task {
        switch self {
        case .moodForTweet(let tweet):
            let contentDict = ["type": "PLAIN_TEXT", "content": tweet]
            let parameters = ["encodingType": "UTF8", "document": contentDict] as [String: Any]
            return .requestCompositeParameters(bodyParameters: parameters, bodyEncoding: JSONEncoding.default, urlParameters: ["key": NetworkManager.shared.currentEnvVars?.apiVariablesGoogle?.apiKey ?? ""])
        }
    }
    
    var headers: [String: String]? {
        return ["Content-type": "application/json"]
    }
}

