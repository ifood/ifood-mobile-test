//
//  UINavigationBar+Extensions.swift
//  TwitterSentiment
//
//  Created by Jean Vinge on 19/09/2018.
//  Copyright © 2018 Jean Vinge. All rights reserved.
//

import UIKit

extension UINavigationBar {

    func configure(theme: Theme) {
        let isTranslucent = theme.navigationBar.background == .clear ? true : false

        self.barTintColor = theme.navigationBar.background
        self.isTranslucent = isTranslucent
        self.tintColor = theme.navigationBar.tintColor
        self.titleTextAttributes = [.foregroundColor: theme.navigationBar.titleColor, .font: theme.navigationBar.font]

        if isTranslucent {
            self.setBackgroundImage(UIImage(), for: .default)
            self.shadowImage = UIImage()
        }
    }
}
