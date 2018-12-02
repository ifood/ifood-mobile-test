//
//  UIColor+Palette.swift
//  SentimentAnalysis
//
//  Created by Thales Frigo on 02/12/18.
//  Copyright © 2018 Thales Frigo. All rights reserved.
//

import UIKit

extension UIColor {
    
    @nonobjc class var materialRed: UIColor {
        return UIColor(
            red: 0xF4/0xFF,
            green: 0x43/0xFF,
            blue: 0x36/0xFF, alpha: 1
        )
    }
    
    @nonobjc class var materialGreen: UIColor {
        return UIColor(
            red: 0x4C/0xFF,
            green: 0xAF/0xFF,
            blue: 0x50/0xFF, alpha: 1
        )
    }
    
    @nonobjc class var materialYellow: UIColor {
        return UIColor(
            red: 0xFF/0xFF,
            green: 0xEB/0xFF,
            blue: 0x3B/0xFF, alpha: 1
        )
    }
}
