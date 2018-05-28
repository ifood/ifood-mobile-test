//
//  Dateformatter+Ext.swift
//  Twimotion
//
//  Created by Antony on 27/05/18.
//  Copyright © 2018 Twimotion. All rights reserved.
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
