//
//  NSNumberExtension.swift
//  IfoodMobileTest
//
//  Created by André Vieira on 09/06/19.
//  Copyright © 2019 André Vieira. All rights reserved.
//

import Foundation

extension NSNumber {
    func isBool() -> Bool { return CFBooleanGetTypeID() == CFGetTypeID(self) }
}
