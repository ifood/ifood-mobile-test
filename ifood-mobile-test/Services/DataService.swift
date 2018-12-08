//
//  DataService.swift
//  ifood-mobile-test
//
//  Created by Marcio Garcia on 30/11/18.
//  Copyright © 2018 oxltech.com. All rights reserved.
//

import Foundation

protocol DataService {
    func requestSentimentAnalysis(text: String,
                                  completion: @escaping (AnalyzedSentiment?, NSError?) -> ())
}
