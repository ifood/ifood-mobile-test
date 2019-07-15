//
//  UIView+Extensions.swift
//  twitter-mood
//
//  Created by Dennis Merli on 7/6/19.
//  Copyright © 2019 Dennis Merli. All rights reserved.
//

import UIKit

extension UIView {
    func lockView(duration: TimeInterval = 0.2) {
        self.endEditing(true)
        if viewWithTag(100) != nil {
            return
        } else {
            let lockView = UIView(frame: UIScreen.main.bounds)
            lockView.backgroundColor = UIColor.black.withAlphaComponent(0.75)
            lockView.tag = 100
            lockView.alpha = 0.0
            let activity = UIActivityIndicatorView(style: .white)
            activity.hidesWhenStopped = true
            activity.center = lockView.center
            activity.translatesAutoresizingMaskIntoConstraints = false
            lockView.addSubview(activity)
            lockView.bringSubviewToFront(activity)
            activity.startAnimating()
            self.addSubview(lockView)
            let xCenterConstraint = NSLayoutConstraint(item: activity, attribute: .centerX, relatedBy: .equal, toItem: lockView, attribute: .centerX, multiplier: 1, constant: 0)
            
            let yCenterConstraint = NSLayoutConstraint(item: activity, attribute: .centerY, relatedBy: .equal, toItem: lockView, attribute: .centerY, multiplier: 1, constant: 0)
            
            NSLayoutConstraint.activate([xCenterConstraint, yCenterConstraint])
            UIView.animate(withDuration: duration) {
                lockView.alpha = 1.0
            }
        }
    }
    
    func unlockView(duration: TimeInterval = 0.2) {
        if let lockView = self.viewWithTag(100) {
            UIView.animate(withDuration: duration, animations: {
                lockView.alpha = 0.0
            }, completion: { _ in
                lockView.removeFromSuperview()
            })
        }
    }
}
