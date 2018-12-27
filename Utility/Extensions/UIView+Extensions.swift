//
//  UIView+Extensions.swift
//  TwitterSentiment
//
//  Created by Jean Vinge on 19/06/2018.
//  Copyright © 2018 Atlas Politico. All rights reserved.
//

import UIKit

extension UIView {
    
    func roundCorners(_ corners: UIRectCorner, radius: CGFloat) {
        let rectShape = CAShapeLayer()
        rectShape.bounds = self.frame
        rectShape.position = self.center
        rectShape.path = UIBezierPath(roundedRect: self.bounds, byRoundingCorners: corners, cornerRadii: CGSize(width: radius, height: radius)).cgPath
        self.layer.mask = rectShape
    }
    
    @objc func size(_ object: Any? = nil) -> CGSize {
        return calculatedSize()
    }
    
    @objc func calculatedSize() -> CGSize {
        self.setNeedsLayout()
        self.layoutIfNeeded()
        return self.systemLayoutSizeFitting(UIView.layoutFittingCompressedSize)
    }
}
