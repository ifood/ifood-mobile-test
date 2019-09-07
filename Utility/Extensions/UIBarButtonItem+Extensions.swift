//
//  UIBarButtonItem+Extensions.swift
//  Utility
//
//  Created by Jean Vinge on 27/12/18.
//  Copyright © 2018 Jean Vinge. All rights reserved.
//

import UIKit

extension UIBarButtonItem {
    
    public convenience init(image: UIImage, target: AnyObject, action: Selector?) {
        self.init(image: image, style: .plain, target: target, action: action)
    }
    
    public convenience init(image: UIImage) {
        self.init(image: image, style: .plain, target: nil, action: nil)
    }

    public convenience init(title: String) {
        self.init(title: title, style: .plain, target: nil, action: nil)
    }
}
