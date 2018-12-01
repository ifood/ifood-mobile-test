//
//  ResultModels.swift
//  ifood-mobile-test
//
//  Created by Marcio Garcia on 01/12/18.
//  Copyright (c) 2018 oxltech.com. All rights reserved.
//
//  This file was generated by the Clean Swift Xcode Templates so
//  you can apply clean architecture to your iOS and Mac projects,
//  see http://clean-swift.com
//

import UIKit

enum Result {
    
    // MARK: Use cases

    enum AnalyzeSentiment {
        struct Request {
            var text: String
        }
        struct Response {
            var analyzedSentiment: AnalyzedSentiment
        }
        struct ViewModel {
            var score: Double
        }
    }
    
    enum Error {
        struct Request {
        }
        struct Response {
            var code: Int
            var message: String
        }
        struct ViewModel {
            var code: Int
            var message: String
        }
    }
}
