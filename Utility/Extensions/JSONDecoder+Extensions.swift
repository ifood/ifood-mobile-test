//
//  JSONDecoder+Extensions.swift
//  Utility
//
//  Created by Jean Vinge on 28/12/18.
//  Copyright © 2018 Jean Vinge. All rights reserved.
//

import Foundation

extension JSONDecoder {
    public static func decode<T: Decodable>(_ classObj: T.Type, from JSON: Any) throws -> T {
        return try JSONDecoder.decode(classObj.self, from: try JSONSerialization.data(withJSONObject: JSON, options: []))
    }
    
    public static func decode<T: Decodable>(_ classObj: T.Type, from mock: MockFile, bundle: Bundle) throws -> T {
        return try JSONDecoder().decode(classObj.self, from: Data.data(with: mock, bundle: bundle))
    }
}
