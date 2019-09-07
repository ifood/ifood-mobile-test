//
//  DocumentSentimentPayload.swift
//  ifood-mobile-test
//
//  Created by Jean Vinge on 26/12/18.
//  Copyright © 2018 Jean Vinge. All rights reserved.
//

import Foundation

public struct DocumentSentimentPayload: Codable {
    public let documentSentiment: DocumentSentiment
    public let language: String
}
