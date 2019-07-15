//
//  TweetDetailCoordinator.swift
//  twitter-mood
//
//  Created by Dennis Merli on 7/7/19.
//  Copyright © 2019 Dennis Merli. All rights reserved.
//

import UIKit

class TweetDetailCoordinator: BaseCoordinator {
    private var tweetDetailViewController: TweetDetailViewController?
    private var tweetDetailViewModel: TweetDetailViewModel?
    private var router: Router!
    
    init(tweetCellViewModel: UserTweetCellViewModel, rootViewControler: UIViewController?, parentCoordinator: BaseCoordinator?) {
        super.init(rootViewControler: rootViewControler, parentCoordinator: parentCoordinator)
        self.router = Router(rootController: rootViewControler)
        tweetDetailViewModel = TweetDetailViewModel(tweetCellViewModel: tweetCellViewModel, googleService: GoogleService())
        
    }
    
    override func start(presentationType: PresentationType) {
        tweetDetailViewController = TweetDetailViewController(viewModel: tweetDetailViewModel as Any, nibName: TweetDetailViewController.typeName)
        
        tweetDetailViewController?.navigationControllerPopped.subscribe(onNext: { [weak self] (_) in
            self?.finalize()
        }).disposed(by: disposeBag)
        
        router.showWithPresentationType(viewController: tweetDetailViewController, presentationType: presentationType)
    }
        
    override func finalize() {
        parentCoordinator?.removeDependency(coordinator: self)
    }
}
