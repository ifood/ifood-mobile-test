//
//  UIView+Anchors.swift
//  tecnonutri
//
//  Created by Thales Frigo on 02/04/18.
//  Copyright © 2018 Grupo Minha Vida. All rights reserved.
//

import UIKit


extension UIView {
    
    @discardableResult func topAnchor(equalTo anchor: NSLayoutYAxisAnchor, constant: CGFloat = 0) -> Self {
        topAnchor.constraint(equalTo: anchor, constant: constant).isActive = true
        return self
    }
    
    @discardableResult func bottomAnchor(equalTo anchor: NSLayoutYAxisAnchor, constant: CGFloat = 0) -> Self {
        bottomAnchor.constraint(equalTo: anchor, constant: constant).isActive = true
        return self
    }
    
    @discardableResult func leadingAnchor(equalTo anchor: NSLayoutXAxisAnchor, constant: CGFloat = 0) -> Self {
        leadingAnchor.constraint(equalTo: anchor, constant: constant).isActive = true
        return self
    }
    
    @discardableResult func trailingAnchor(equalTo anchor: NSLayoutXAxisAnchor, constant: CGFloat = 0) -> Self {
        trailingAnchor.constraint(equalTo: anchor, constant: constant).isActive = true
        return self
    }
    
    @discardableResult func heightAnchor(equalTo anchor: NSLayoutDimension, constant: CGFloat = 0) -> Self {
        heightAnchor.constraint(equalTo: anchor, constant: constant).isActive = true
        return self
    }
    
    @discardableResult func heightAnchor(equalTo height: CGFloat) -> Self {
        heightAnchor.constraint(equalToConstant: height).isActive = true
        return self
    }
    
    @discardableResult func widthAnchor(equalTo anchor: NSLayoutDimension, constant: CGFloat = 0) -> Self {
        widthAnchor.constraint(equalTo: anchor, constant: constant).isActive = true
        return self
    }
    
    @discardableResult func widthAnchor(equalTo width: CGFloat) -> Self {
        widthAnchor.constraint(equalToConstant: width).isActive = true
        return self
    }
    
    @discardableResult func centerXAnchor(equalTo anchor: NSLayoutXAxisAnchor) -> Self {
        centerXAnchor.constraint(equalTo: anchor).isActive = true
        return self
    }
    
    @discardableResult func centerYAnchor(equalTo anchor: NSLayoutYAxisAnchor) -> Self {
        centerYAnchor.constraint(equalTo: anchor).isActive = true
        return self
    }
    
    @discardableResult func anchors(equalTo superview: UIView, constant: CGFloat = 0) -> Self {
        return self
            .topAnchor(equalTo: superview.topAnchor, constant: constant)
            .bottomAnchor(equalTo: superview.bottomAnchor, constant: constant * -1)
            .leadingAnchor(equalTo: superview.leadingAnchor, constant: constant)
            .trailingAnchor(equalTo: superview.trailingAnchor, constant: constant * -1)
    }
}
