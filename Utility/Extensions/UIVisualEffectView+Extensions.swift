//
//  UIVisualEffect+Extensions.swift
//  TwitterSentiment
//
//  Created by Jean Vinge on 09/11/18.
//  Copyright © 2018 Jean Vinge. All rights reserved.
//

import UIKit

extension UIVisualEffectView {
    public convenience init(backgroundColor: UIColor, alpha: CGFloat) {
        self.init()
        self.backgroundColor = backgroundColor
        self.alpha = alpha
    }
}
