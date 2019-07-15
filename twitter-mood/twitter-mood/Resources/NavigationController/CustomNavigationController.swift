//
//  CustomNavigationController.swift
//  twitter-mood
//
//  Created by Dennis Merli on 7/9/19.
//  Copyright © 2019 Dennis Merli. All rights reserved.
//

import UIKit

class CustomNavigationController: UINavigationController {
    
    override init(rootViewController: UIViewController) {
        super.init(rootViewController: rootViewController)
        setupStyle()
    }
    
    required init?(coder aDecoder: NSCoder) {
        super.init(coder: aDecoder)
        setupStyle()
    }
    
    override init(nibName nibNameOrNil: String?, bundle nibBundleOrNil: Bundle?) {
        super.init(nibName: nibNameOrNil, bundle: nibBundleOrNil)
        setupStyle()
    }
    
    private func setupStyle() {
        self.navigationBar.isTranslucent = false
        self.navigationBar.barTintColor = UIKit.grey323232
        self.navigationBar.backgroundColor = UIKit.grey323232
        
        self.navigationBar.titleTextAttributes = [NSAttributedString.Key.foregroundColor: UIColor.white]
        self.navigationBar.tintColor = UIColor.white
        setNeedsStatusBarAppearanceUpdate()
    }
}
