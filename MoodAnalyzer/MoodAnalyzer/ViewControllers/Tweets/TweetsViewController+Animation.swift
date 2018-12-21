//
//  TweetsViewController+Animation.swift
//  MoodAnalyzer
//
//  Created by Carlos Henrique Salvador on 20/12/18.
//  Copyright © 2018 Carlos Henrique Salvador. All rights reserved.
//

import UIKit

extension TweetsViewController : UIScrollViewDelegate {
    
    func scrollViewDidScroll(_ scrollView: UIScrollView) {
//        self.animate(shouldCollapse: scrollView.contentOffset.y < 0 ? true : false)
    }
    
}

extension TweetsViewController {
    
    func animate(shouldCollapse: Bool) {
        
        self.profileImageCenterYConstraint.priority = shouldCollapse ? UILayoutPriority.defaultHigh : UILayoutPriority.defaultLow
        self.profileImageTrailingConstraint.priority = shouldCollapse ? UILayoutPriority.defaultLow : UILayoutPriority.defaultHigh
        
        
        UIView.animate(withDuration: 0.3) {
            self.view.layoutIfNeeded()
            self.view.updateConstraints()
        }
        
    }
    
}
