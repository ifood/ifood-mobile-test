//
//  UIFont+Custom.swift
//  my-tweets
//
//  Created by Gabriel Catice on 25/11/18.
//  Copyright © 2018 Gabriel Catice. All rights reserved.
//

import UIKit

extension UIFont {
    enum DefaultFont: String {
        case regular = "HelveticaNeue-Regular"
        case bold = "HelveticaNeue-Bold"
        case semibold = "HelveticaNeue-SemiBold"
        case extraBold = "HelveticaNeue-ExtraBold"
    }
    
    class func defaultFont(_ type: DefaultFont, size: CGFloat) -> UIFont {
        return UIFont(name: type.rawValue, size: size) ?? UIFont.systemFont(ofSize: size)
    }
}

