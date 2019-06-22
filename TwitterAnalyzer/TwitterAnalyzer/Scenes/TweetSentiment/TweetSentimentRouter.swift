//
//  TweetSentimentRouter.swift
//  TwitterAnalyzer
//
//  Created by Bruno Vieira on 18/06/19.
//  Copyright © 2019 Bruno Vieira. All rights reserved.
//

import UIKit

@objc protocol TweetSentimentRoutingLogic {
    
}

protocol TweetSentimentDataPassing {
    var dataStore: TweetSentimentDataStore? { get }
}

class TweetSentimentRouter: NSObject, TweetSentimentRoutingLogic, TweetSentimentDataPassing {
    weak var viewController: TweetSentimentViewController?
    var dataStore: TweetSentimentDataStore?
}
