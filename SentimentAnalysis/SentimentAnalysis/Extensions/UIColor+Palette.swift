//
//  UIColor+Palette.swift
//  SentimentAnalysis
//
//  Created by Thales Frigo on 02/12/18.
//  Copyright © 2018 Thales Frigo. All rights reserved.
//

import UIKit

extension UIColor {
    
    @nonobjc class var primary: UIColor {
        return UIColor(
            red: 0x94/0xFF,
            green: 0x00/0xFF,
            blue: 0xD3/0xFF, alpha: 1
        )
    }
    
    @nonobjc class var materialYellow: UIColor {
        return UIColor(
            red: 0xFF/0xFF,
            green: 0xFF/0xFF,
            blue: 0x00/0xFF, alpha: 1
        )
    }
    
    @nonobjc class var materialGray: UIColor {
        return UIColor(
            red: 0x45/0xFF,
            green: 0x5A/0xFF,
            blue: 0x64/0xFF, alpha: 1
        )
    }
    
    @nonobjc class var materialBlue: UIColor {
        return UIColor(
            red: 0x21/0xFF,
            green: 0x96/0xFF,
            blue: 0xF3/0xFF, alpha: 1
        )
    }
}
