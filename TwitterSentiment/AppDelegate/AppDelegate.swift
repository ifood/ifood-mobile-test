//
//  AppDelegate.swift
//  TwitterSentiment
//
//  Created by Jean Vinge on 27/12/18.
//  Copyright © 2018 Jean Vinge. All rights reserved.
//

import UIKit

@UIApplicationMain
class AppDelegate: UIResponder, UIApplicationDelegate {

    // MARK: Var
    
    lazy var coordinator = MainCoordinator()
    let window = UIWindow(frame: UIScreen.main.bounds)
    
    // MARK: Init
    
    func application(_ application: UIApplication, didFinishLaunchingWithOptions launchOptions: [UIApplication.LaunchOptionsKey: Any]? = nil) -> Bool {
        self.coordinator.start(window: self.window)
        return true
    }
}
