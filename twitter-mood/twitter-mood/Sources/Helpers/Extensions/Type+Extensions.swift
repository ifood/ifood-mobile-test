//
//  Type+Extensions.swift
//  twitter-mood
//
//  Created by Dennis Merli on 7/9/19.
//  Copyright © 2019 Dennis Merli. All rights reserved.
//

import Foundation

protocol TypeName: AnyObject {
    static var typeName: String { get }
}

// Swift Objects
extension TypeName {
    static var typeName: String {
        let type = String(describing: self)
        return type
    }
}

// Bridge to Obj-C
extension NSObject: TypeName {
    class var typeName: String {
        let type = String(describing: self)
        return type
    }
}
