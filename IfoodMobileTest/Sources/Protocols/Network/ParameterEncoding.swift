//
//  ParameterEncoding.swift
//  IfoodMobileTest
//
//  Created by André Vieira on 09/06/19.
//  Copyright © 2019 André Vieira. All rights reserved.
//

import Foundation

public protocol ParameterEncoding {
    func encode(request: URLRequest?, parameters: [String: Any]?) throws -> URLRequest
}
