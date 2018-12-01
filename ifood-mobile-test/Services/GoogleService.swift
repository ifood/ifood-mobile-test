//
//  GoogleService.swift
//  ifood-mobile-test
//
//  Created by Marcio Garcia on 30/11/18.
//  Copyright © 2018 oxltech.com. All rights reserved.
//

import Foundation

protocol GoogleService {
    func requestSentimentAnalysis(text: String,
                                  completion: @escaping (String?, NSError?) -> ())
}
