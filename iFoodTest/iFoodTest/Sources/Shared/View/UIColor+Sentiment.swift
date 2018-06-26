//
//  UIColor+Sentiment.swift
//  iFoodTest
//
//  Created by Mauricio Cesar Maniglia Junior on 10/06/18.
//  Copyright © 2018 Mauricio Cesar Maniglia Junior. All rights reserved.
//

import UIKit

extension UIColor {
    
    convenience init(red: Int, green: Int, blue: Int) {
        assert(red >= 0 && red <= 255, "Invalid red component")
        assert(green >= 0 && green <= 255, "Invalid green component")
        assert(blue >= 0 && blue <= 255, "Invalid blue component")
        self.init(red: CGFloat(red) / 255.0, green: CGFloat(green) / 255.0, blue: CGFloat(blue) / 255.0, alpha: 1.0)
    }
    
    convenience init(netHex:Int) {
        self.init(red:(netHex >> 16) & 0xff, green:(netHex >> 8) & 0xff, blue:netHex & 0xff)
    }
    
    static func happy() -> UIColor {
        return UIColor(netHex: 0xF2D46F)
    }
    
    static func sad() -> UIColor {
        return UIColor(netHex: 0x2F6CAD)
    }
    
    static func neutral() -> UIColor {
        return UIColor(netHex: 0x75706B)
    }
}
