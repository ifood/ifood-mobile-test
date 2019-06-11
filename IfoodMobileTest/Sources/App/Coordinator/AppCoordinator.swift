//
//  AppCoordinator.swift
//  IfoodMobileTest
//
//  Created by André Vieira on 09/06/19.
//  Copyright © 2019 André Vieira. All rights reserved.
//

import Foundation
import RxSwift

final class AppCoordinator: BaseCoordinator<Void> {
    
    private var navController: UINavigationController
    
    init(navigationController: UINavigationController) {
        self.navController = navigationController
    }
    
    override func start() -> Observable<Void> {
        let coordinator = TwitterCoordinator(navController: navController)
        return coordinate(to: coordinator)
    }
}
