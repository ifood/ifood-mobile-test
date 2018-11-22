//
//  UIView+Extensions.swift
//  my-tweets
//
//  Created by Gabriel Catice on 22/11/18.
//  Copyright © 2018 Gabriel Catice. All rights reserved.
//

import UIKit

// Fade
extension UIView {
    func linearFadeIn(withDuration duration: TimeInterval) {
        self.isHidden = false
        
        UIView.animate(withDuration: duration, animations: { () -> Void in
            self.alpha = 1.0
        })
    }
    
    func linearFadeOut(withDuration duration: TimeInterval) {
        UIView.animate(withDuration: duration, animations: { () -> Void in
            self.alpha = 0.0
        }) { completed in
            if completed {
                self.isHidden = true
            }
        }
    }
}
