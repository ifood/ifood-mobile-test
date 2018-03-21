//
//  AppDelegate.swift
//  twitments
//
//  Created by Ezequiel on 07/03/18.
//  Copyright © 2018 Ezequiel. All rights reserved.
//

import UIKit
import CoreData

@UIApplicationMain
class AppDelegate: UIResponder, UIApplicationDelegate {

    var window: UIWindow?

    func application(_ application: UIApplication, didFinishLaunchingWithOptions launchOptions: [UIApplicationLaunchOptionsKey: Any]?) -> Bool {
        ConfigurationCreator.initializer()
        Requester.shared.setupAlamofire()
        let rootModule = AddHandlerModule()
        window = UIWindow(frame: UIScreen.main.bounds)
        window?.rootViewController = rootModule.view
        window?.backgroundColor = .white
        window?.makeKeyAndVisible()
        return true
    }
}
