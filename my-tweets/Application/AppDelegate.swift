//
//  AppDelegate.swift
//  my-tweets
//
//  Created by Gabriel Catice on 22/11/18.
//  Copyright © 2018 Gabriel Catice. All rights reserved.
//

import UIKit
import Swinject
import SwinjectAutoregistration
import IQKeyboardManagerSwift
import TwitterKit

@UIApplicationMain
class AppDelegate: UIResponder, UIApplicationDelegate {
    
    var coordinator: ApplicationCoordinator!
    var window: UIWindow?
    
    func application(_ application: UIApplication,
                     didFinishLaunchingWithOptions launchOptions: [UIApplication.LaunchOptionsKey: Any]?) -> Bool {
        
        let newWindow = UIWindow(frame: UIScreen.main.bounds)
        newWindow.backgroundColor = .white
        self.window = newWindow
        
        coordinator = ApplicationCoordinator(window: newWindow)
        coordinator.start()
        
        IQKeyboardManager.shared.enable = true
        IQKeyboardManager.shared.enableAutoToolbar = true
        IQKeyboardManager.shared.shouldShowToolbarPlaceholder = true
        IQKeyboardManager.shared.toolbarManageBehaviour = .byTag
        IQKeyboardManager.shared.keyboardDistanceFromTextField = 30.0
    
        
        return true
    }
    
    func application(_ app: UIApplication, open url: URL, options: [UIApplication.OpenURLOptionsKey : Any] = [:]) -> Bool {
        return TWTRTwitter.sharedInstance().application(app, open: url, options: options)
    }
}
