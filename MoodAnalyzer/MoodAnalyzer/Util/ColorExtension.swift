//
//  ColorExtension.swift
//  MoodAnalyzer
//
//  Created by Carlos Henrique Salvador on 19/12/18.
//  Copyright © 2018 Carlos Henrique Salvador. All rights reserved.
//

import UIKit

extension UIColor {
    
    // MARK: - Custom Colors
    class var cornflowerBlue : UIColor {
        return UIColor(hex: "836FF8")
    }
    
    class var bilobaFlowerPink : UIColor {
        return UIColor(hex: "E78EEB")
    }
    
    class var downriverBlue : UIColor {
        return UIColor(hex: "0B0F49")
    }
    
    class var charcoalGray : UIColor {
        return UIColor(hex: "484848")
    }
    
    class var candleLightYellow : UIColor {
        return UIColor(hex: "FED40C")
    }
    
    class var persianBlue : UIColor {
        return UIColor(hex: "0D33CD")
    }
    
    // MARK: - Convert HEX to RGB
    convenience init(hex: String, alpha: CGFloat? = 1.0) {
        let scanner = Scanner(string: hex)
        scanner.scanLocation = 0
        
        var rgbValue: UInt64 = 0
        
        scanner.scanHexInt64(&rgbValue)
        
        let r = (rgbValue & 0xff0000) >> 16
        let g = (rgbValue & 0xff00) >> 8
        let b = rgbValue & 0xff
        
        self.init(
            red: CGFloat(r) / 0xff,
            green: CGFloat(g) / 0xff,
            blue: CGFloat(b) / 0xff, alpha: alpha ?? 1.0
        )
    }
    
}
