//
//  AuthFactory.swift
//  SentimentAnalysisTests
//
//  Created by Thales Frigo on 03/12/18.
//  Copyright © 2018 Thales Frigo. All rights reserved.
//

import Foundation
@testable import SentimentAnalysis

struct AuthFactory {
    static func makeAuth() -> Auth {
        return Auth(token: "AAAAAAAAAAA")
    }
}
