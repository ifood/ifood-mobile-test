//
//  RootRouter.swift
//  iFoodTest
//
//  Created by Mauricio Cesar Maniglia Junior on 06/06/18.
//  Copyright © 2018 Mauricio Cesar Maniglia Junior. All rights reserved.
//

import UIKit

final class RootRouter: RootRouterProtocol {
    
    func presentTwitterUserScreen(in window: UIWindow) {
        window.makeKeyAndVisible()
        window.rootViewController = TwitterUserBuilder.assembleModule()
    }
}
