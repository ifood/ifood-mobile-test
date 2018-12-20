//
//  CALayerExtension.swift
//  MoodAnalyzer
//
//  Created by Carlos Henrique Salvador on 19/12/18.
//  Copyright © 2018 Carlos Henrique Salvador. All rights reserved.
//

import UIKit

extension CALayer {
    
    static func setDropShadow(layer : CALayer, radius : CGFloat, opacity : Float, color: UIColor) {
        layer.shadowOffset = CGSize(width: 0.0, height: 0.0)
        layer.shadowRadius = radius
        layer.shadowColor = color.cgColor
        layer.shadowOpacity = opacity
    }
    
    static func setDropShadowBottom(layer : CALayer, opacity : Float, color : UIColor) {
        layer.shadowOffset = CGSize(width: 0, height: 3)
        layer.shadowOpacity = opacity
        layer.shadowRadius = 5.0
        layer.shadowColor = color.cgColor
    }
    
    static func removeDropShadow(layer: CALayer){
        CALayer.setDropShadow(layer: layer, radius: 0, opacity: 0, color: .clear)
    }
}

