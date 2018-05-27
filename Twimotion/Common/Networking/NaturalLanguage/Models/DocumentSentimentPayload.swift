//
//  DocumentSentimentPayload.swift
//  Twimotion
//
//  Created by Antony on 27/05/18.
//  Copyright © 2018 Twimotion. All rights reserved.
//

import Foundation

struct DocumentSentimentPayload: Decodable {
    let documentSentiment: DocumentSentiment
    let language: String
}
