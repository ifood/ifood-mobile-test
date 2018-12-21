//
//  UsernameViewController+Keyboard.swift
//  MoodAnalyzer
//
//  Created by Carlos Henrique Salvador on 19/12/18.
//  Copyright © 2018 Carlos Henrique Salvador. All rights reserved.
//

import UIKit

extension UsernameViewController {
    
    @objc func keyboardWillShow(notification: NSNotification) {
        self.animate(shouldShow: true,  notification: notification)
    }
    
    @objc func keyboardWillHide(notification: NSNotification) {
        self.animate(shouldShow: false, notification: notification)
    }
    
    func animate(shouldShow : Bool, notification: NSNotification) {
        let keyboardInfo:NSDictionary = notification.userInfo! as NSDictionary
        let keyboardAnimationDuration = keyboardInfo.value(forKey: UIResponder.keyboardAnimationDurationUserInfoKey) as? Double ?? 0.3
        let keyboardFrameBegin = keyboardInfo.value(forKey: UIResponder.keyboardFrameEndUserInfoKey) as AnyObject
        if let keyboardFrameBeginRect = keyboardFrameBegin.cgRectValue {
            self.textFieldCenterYConstraint.priority = shouldShow ? UILayoutPriority.defaultLow : UILayoutPriority.defaultHigh
            self.textFieldBottomConstraint.priority = shouldShow ? UILayoutPriority.defaultHigh : UILayoutPriority.defaultLow
            self.analyzeButtonBottomConstraint.constant = shouldShow ? 16.0 + keyboardFrameBeginRect.size.height : 16.0
        }
        
        UIView.animate(withDuration: keyboardAnimationDuration) {
            self.view.layoutIfNeeded()
            self.view.updateConstraints()
        }
    }
    
}
