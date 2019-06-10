//
//  TwitterCoondinator.swift
//  IfoodMobileTest
//
//  Created by André Vieira on 09/06/19.
//  Copyright © 2019 André Vieira. All rights reserved.
//

import Foundation
import RxSwift

final class TwitterCoordinator: BaseCoordinator<Void> {
    private var navController: UINavigationController
    private var bag = DisposeBag()
    
    init(navController: UINavigationController) {
        self.navController = navController
    }
    
    override func start() -> Observable<Void> {
        let controller = FindTwitterController(viewModel: FindTwitterViewModel())
        navController.pushViewController(controller, animated: true)
        controller.onList.subscribe(onNext: {[weak self] tweets in
            self?.navigationToList(tweets: tweets)
        }).disposed(by: bag)
        return Observable.never()
    }
    
    private func navigationToList(tweets: [TweetModel]) {
        print(tweets)
    }
}
