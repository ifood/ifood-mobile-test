//
//  Task.swift
//  IfoodMobileTest
//
//  Created by André Vieira on 10/06/19.
//  Copyright © 2019 André Vieira. All rights reserved.
//

import Foundation

// swiftlint:disable all
public enum Task {
    case requestParameters(parameters: [String: Any], encoding: ParameterEncoding)
    case requestCompositeParameters(bodyParameters: [String: Any], bodyEncoding: ParameterEncoding, urlParameters: [String: Any])
}
