//
//  NSObject+Extensions.swift
//  TwitterSentiment
//
//  Created by Jean Vinge on 18/06/2018.
//  Copyright © 2018 Atlas Politico. All rights reserved.
//

import UIKit

extension NSObject {
    class var className: String {
        return String(describing: self)
    }
}
