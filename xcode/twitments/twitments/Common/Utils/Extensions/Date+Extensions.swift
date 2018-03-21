//
//  Date+Extensions.swift
//  twitments
//
//  Created by Ezequiel on 08/03/18.
//  Copyright © 2018 Ezequiel. All rights reserved.
//

import Foundation
extension String {
    func formatted() -> String {

        let dateFormatter = DateFormatter()
        dateFormatter.dateFormat = "EEE MMM dd HH:mm:ss ZZZZZ yyyy"
        dateFormatter.calendar = Calendar(identifier: .iso8601)
        dateFormatter.timeZone = TimeZone(identifier: "GMT")
        dateFormatter.locale = Locale(identifier: "en_US_POSIX")
        guard let value = dateFormatter.date(from: self) else {
            return ""
        }
        return dateFormatter.string(from: value)
    }
}
