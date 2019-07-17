//
//  Sentiment.swift
//  Movile-Test
//
//  Created by talves on 17/07/19.
//  Copyright © 2019 Thalves. All rights reserved.
//

import UIKit

struct DocumentSentiment: THCodable {
    var magnitude: Double
    var score: Double
}

struct Sentiment: THCodable {

    var language: String
    var documentSentiment: DocumentSentiment
}
