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
    
    private var navigationController: UINavigationController
    private var service: OAuthService
    private let bag = DisposeBag()
    
    init(navigationController: UINavigationController, service: OAuthService = OAuthServiceImpl()) {
        self.navigationController = navigationController
        self.service = service
    }
    
    override func start() -> Observable<Void> {
        let viewController = ViewController(nibName: nil, bundle: nil)
        navigationController.pushViewController(viewController, animated: false)
        service.getAccessToken().subscribe(onNext: { oauth in
            print(oauth)
        }, onError: { error in
            print(error.localizedDescription)
        }).disposed(by: bag)
        
        return Observable.just(Void())
    }
}
