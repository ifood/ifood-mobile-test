//
//  SplashViewController.swift
//  SentimentAnalysis
//
//  Created by Thales Frigo on 01/12/18.
//  Copyright © 2018 Thales Frigo. All rights reserved.
//

import UIKit
import RevealingSplashView

class SplashViewController: UIViewController {
    
    let splashView = RevealingSplashView.makeSplash()
    
    override func viewDidLoad() {
        super.viewDidLoad()
        view.addSubview(splashView)
        splashView.startAnimation()
        
        
    }
}
