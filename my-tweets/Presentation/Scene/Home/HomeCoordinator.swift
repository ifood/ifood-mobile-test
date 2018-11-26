//
//  HomeCoordinator.swift
//  my-tweets
//
//  Created by Gabriel Catice on 25/11/18.
//  Copyright © 2018 Gabriel Catice. All rights reserved.
//

import UIKit
import RxSwift

class HomeCoordinator: Coordinator, DisposableHolder {
    var disposeBag: DisposeBag = DisposeBag()
    var children: [Coordinator] = []
    
    var navigationController: UINavigationController
    var onStopSubject: PublishSubject<Void> = PublishSubject<Void>()
    
    init(navigationController: UINavigationController) {
        self.navigationController = navigationController
    }
    
    func start(_ presentation: CoordinatorPresentation = .push) {
        guard let viewController = HomeViewControllerConfigurator.viewController() else { return }
        
        let backButton = UIBarButtonItem(title: "", style: .plain, target: nil, action: nil)
        viewController.navigationItem.title = R.string.localizable.app_name()
        viewController.navigationItem.backBarButtonItem = backButton
        
        navigationController.pushViewController(viewController, animated: true)
    }
}
