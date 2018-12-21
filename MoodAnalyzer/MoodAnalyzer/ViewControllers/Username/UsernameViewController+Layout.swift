//
//  UsernameViewController+Layout.swift
//  MoodAnalyzer
//
//  Created by Carlos Henrique Salvador on 19/12/18.
//  Copyright © 2018 Carlos Henrique Salvador. All rights reserved.
//

import UIKit

extension UsernameViewController {
    
    func animateEmojis() {
        
        UIView.animateKeyframes(withDuration: 6.0, delay: 10.0, options: .calculationModeLinear, animations: {
            
            UIView.addKeyframe(withRelativeStartTime: 0.0, relativeDuration: 0.5) {
                self.emojiLabel.text = "😄"
            }
            
            UIView.addKeyframe(withRelativeStartTime: 0.5, relativeDuration: 1.0) {
               self.emojiLabel.text = "😐"
            }
            
            UIView.addKeyframe(withRelativeStartTime: 1.0, relativeDuration: 1.5) {
                self.emojiLabel.text = "☹️"
            }
        })
    }
    
}
