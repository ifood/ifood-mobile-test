//
//  DateFormatter+TwitterDateFormat.swift
//  SentimentAnalysis
//
//  Created by Thales Frigo on 28/11/18.
//  Copyright © 2018 Thales Frigo. All rights reserved.
//

import Foundation

extension DateFormatter {
    static let twitterDateFormat: DateFormatter = {
        let formatter = DateFormatter()
        formatter.dateFormat = "EEE MMM dd HH:mm:ss Z yyyy"
        formatter.locale = Locale(identifier: "en_US_POSIX")
        return formatter
    }()
}
