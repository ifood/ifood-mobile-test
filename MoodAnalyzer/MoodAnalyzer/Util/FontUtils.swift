//
//  FontUtils.swift
//  MoodAnalyzer
//
//  Created by Carlos Henrique Salvador on 19/12/18.
//  Copyright © 2018 Carlos Henrique Salvador. All rights reserved.
//

import UIKit

class FontUtils {

    static func getScaledFont(forFont name: String, textStyle: UIFont.TextStyle) -> UIFont {
        let userFont =  UIFontDescriptor.preferredFontDescriptor(withTextStyle: textStyle)
        let pointSize = userFont.pointSize
        guard let customFont = UIFont(name: name, size: pointSize) else {
            fatalError("No font")
        }
        return UIFontMetrics.default.scaledFont(for: customFont)
    }
    
}
