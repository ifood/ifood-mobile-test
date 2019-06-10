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
        controller
            .onList
            .observeOn(MainScheduler.instance)
            .subscribe(onNext: {[weak self] tweets in
            self?.navigationToList(tweets: tweets)
        }).disposed(by: bag)
        return Observable.never()
    }
    
    private func navigationToList(tweets: [TweetModel]) {
        let controller = TweetListController(viewModel: TweetListViewModel(tweets: tweets))
        controller.onBack.subscribe(onNext: {[weak self]  in
            self?.navController.popViewController(animated: true)
        }).disposed(by: bag)
        controller.onSentimentAnalyzer.subscribe(onNext: {[weak self] text in
            self?.navigationToonSentimentAnalyzer(text: text)
        }).disposed(by: bag)
        navController.pushViewController(controller, animated: true)
    }
    
    private func navigationToonSentimentAnalyzer(text: String) {
        print(text)
    }
}
