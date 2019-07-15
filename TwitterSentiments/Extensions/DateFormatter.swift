//
//  DateFormatter.swift
//  TwitterSentiments
//
//  Created by Marina Azevedo on 14/07/19.
//  Copyright © 2019 Marina Azevedo. All rights reserved.
//

import Foundation

extension DateFormatter {
    
    convenience init(format: String) {
        self.init()
        dateFormat = format
    }
    
    static let E_MMM_d_HHmmss_Z_yyyy: DateFormatter = {
        let fmt = DateFormatter()
        fmt.dateFormat = "E MMM d HH:mm:ss Z yyyy"
        fmt.isLenient = true
        fmt.calendar = Calendar(identifier: .iso8601)
        fmt.timeZone = TimeZone.current
        fmt.locale = Locale.current
        return fmt
    }()
}
