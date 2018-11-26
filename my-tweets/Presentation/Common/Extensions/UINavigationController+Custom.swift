//
//  UINavigationController+Custom.swift
//  my-tweets
//
//  Created by Gabriel Catice on 22/11/18.
//  Copyright © 2018 Gabriel Catice. All rights reserved.
//

import UIKit

extension UINavigationController {
    func setDefaultNavigationBarApperance() {
        navigationBar.barStyle = .default
        navigationBar.isTranslucent = false
        navigationBar.barTintColor = .white
        navigationBar.tintColor = UIColor(red: 56.0 / 255.0, green: 161.0 / 255.0, blue: 243.0 / 255.0, alpha: 1.0)
        navigationBar.largeTitleTextAttributes = [NSAttributedString.Key.foregroundColor: UIColor(red: 56.0 / 255.0, green: 161.0 / 255.0, blue: 243.0 / 255.0, alpha: 1.0),
                                                  NSAttributedString.Key.font: UIFont.defaultFont(.bold, size: 28)]
        navigationBar.prefersLargeTitles = true
        
        // Sets Bar's Shadow Image (Color) //
        navigationBar.shadowImage = UIImage.imageWithColor(color: UIColor(red: 56.0 / 255.0, green: 161.0 / 255.0, blue: 243.0 / 255.0, alpha: 1.0))
    }
}

extension UIImage {
    class func imageWithColor(color: UIColor) -> UIImage {
        let rect = CGRect(x: 0.0, y: 0.0, width: 1.0, height: 0.5)
        UIGraphicsBeginImageContextWithOptions(rect.size, false, 0.0)
        color.setFill()
        UIRectFill(rect)
        let image: UIImage = UIGraphicsGetImageFromCurrentImageContext()!
        UIGraphicsEndImageContext()
        return image
    }
}
