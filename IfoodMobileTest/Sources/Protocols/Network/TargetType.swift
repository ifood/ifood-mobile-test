//
//  TargetType.swift
//  IfoodMobileTest
//
//  Created by André Vieira on 09/06/19.
//  Copyright © 2019 André Vieira. All rights reserved.
//

import Foundation

public protocol TargetType {
    
    var baseURL: URL { get }
    
    var path: String { get }
    
    var method: HTTPMethod { get }
    
    var headers: [String: String]? { get }
    
    var parameters: [String: Any]? { get }
    
    var parameterEncoding: ParameterEncoding { get }
}
