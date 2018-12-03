//
//  UIViewController+ChildController.swift
//  tecnonutri
//
//  Created by Thales Frigo on 29/05/18.
//  Copyright © 2018 Grupo Minha Vida. All rights reserved.
//

import UIKit

extension UIViewController {
    
    func add(_ child: UIViewController) {
        // Add Child View Controller
        addChild(child)
        
        // Add Child View as Subview
        view.addSubview(child.view)
        
        // Configure Child View
        child.view.frame = view.bounds
        child.view.autoresizingMask = [.flexibleWidth, .flexibleHeight]
        
        // Notify Child View Controller
        child.didMove(toParent: self)
    }
    
    func add(_ child: UIViewController, with frame: CGRect) {
        // Add Child View Controller
        addChild(child)
        
        // Add Child View as Subview
        view.addSubview(child.view)
        
        // Configure Child View
        child.view.frame = frame
        child.view.autoresizingMask = [.flexibleWidth, .flexibleHeight]
        
        // Notify Child View Controller
        child.didMove(toParent: self)
    }
    
    func remove() {
        // Guarantee that parent it is not nil
        guard parent != nil else {
            return
        }
        
        // Notify Child View Controller
        willMove(toParent: nil)
        
        // Remove Child View From Superview
        view.removeFromSuperview()
        
        // Notify Child View Controller
        removeFromParent()
    }
    
    func removeAnimated(){
        guard parent != nil else {
            return
        }
        
        UIView.animate(withDuration: 0.3, animations: { [weak self] in
            self?.view.alpha = 0
        }) { [weak self] (completion) in
            self?.remove()
            self?.view.alpha = 1
        }
    }
}
