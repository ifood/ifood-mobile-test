//
//  UIColor+Extensions.swift
//  twitments
//
//  Created by Ezequiel on 08/03/18.
//  Copyright © 2018 Ezequiel. All rights reserved.
//

import Foundation
import UIKit

extension UIColor {
    convenience init(red: Int, green: Int, blue: Int) {
        assert(red >= 0 && red <= 255, "Invalid red component")
        assert(green >= 0 && green <= 255, "Invalid green component")
        assert(blue >= 0 && blue <= 255, "Invalid blue component")
        
        self.init(red: CGFloat(red) / 255.0, green: CGFloat(green) / 255.0, blue: CGFloat(blue) / 255.0, alpha: 1.0)
    }
    
    convenience init(rgb: Int) {
        self.init(
            red: (rgb >> 16) & 0xFF,
            green: (rgb >> 8) & 0xFF,
            blue: rgb & 0xFF
        )
    }
}

extension UIColor {
    
    static func emotionalColor( _ feeling: Feeling) -> UIColor {
        
        switch feeling {
        case .happy:
            return UIColor(rgb: 0xFFD304)
        case .normal:
            return UIColor.lightGray
        case .bad:
            return UIColor(rgb: 0x57ABF0)
        case .none:
            return UIColor.white
        }
    }
}
