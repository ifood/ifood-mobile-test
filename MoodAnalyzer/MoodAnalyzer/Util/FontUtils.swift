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
        
        for family: String in UIFont.familyNames
        {
            print("\(family)")
            for names: String in UIFont.fontNames(forFamilyName: family)
            {
                print("== \(names)")
            }
        }
        
        
        let userFont =  UIFontDescriptor.preferredFontDescriptor(withTextStyle: textStyle)
        let pointSize = userFont.pointSize
        guard let customFont = UIFont(name: name, size: pointSize) else {
            fatalError("No font")
        }
        return UIFontMetrics.default.scaledFont(for: customFont)
    }
    
}
