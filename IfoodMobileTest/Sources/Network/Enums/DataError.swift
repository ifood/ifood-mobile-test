//
//  DataError.swift
//  IfoodMobileTest
//
//  Created by André Vieira on 09/06/19.
//  Copyright © 2019 André Vieira. All rights reserved.
//

import Foundation

public enum DataError: Swift.Error {
    case jsonParse(JSONParseError, Any)
    case statusCode(Int)
    case underlying(Swift.Error)
    case generic(message: String)
    case withoutInternet
}

public enum JSONParseError: Swift.Error {
    case invalid(Any)
    case convertibleError(value: Any?, type: Any.Type)
    case customError(field: String?, message: String)
    case invalidRawValueError(field: String, value: Any, type: Any.Type)
    case missingFieldError(field: String)
    case typeMismatchError(field: String, value: Any, type: Any.Type)
}
