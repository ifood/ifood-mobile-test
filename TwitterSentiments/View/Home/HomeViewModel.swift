//
//  HomeScreenViewModel.swift
//  TwitterSentiments
//
//  Created by Marina Azevedo on 14/07/19.
//  Copyright © 2019 Marina Azevedo. All rights reserved.
//

import Foundation
import RxSwift

protocol HomeViewModelType {
    //inputs
    var username: Variable<String> { get }
    var listTweets: PublishSubject<Void> { get }
    //outputs
    var couldNotLoadUser: PublishSubject<Void> { get }
    var isLoadingUser: BehaviorSubject<Bool> { get }
}

protocol HomeViewModelDelegate: class {
    func didLoadUser(user: User)
}

class HomeViewModel: HomeViewModelType {
    
    var twitterDataSource: TwitterDataSourceType
    var disposeBag = DisposeBag()
    
    weak var delegate: HomeViewModelDelegate?
    
    // MARK: - Rx
    // inputs
    let username = Variable<String>("")
    let listTweets = PublishSubject<Void>()
    
    //outputs
    let couldNotLoadUser = PublishSubject<Void>()
    let isLoadingUser = BehaviorSubject<Bool>(value: false)
    
    // MARK: - Initializers
    
    init(twitterDataSource: TwitterDataSourceType) {
        self.twitterDataSource = twitterDataSource
        
        // when tap listTweets button should loadUser with username
        listTweets.withLatestFrom(username.asObservable())
            .bind(onNext: loadUser)
            .disposed(by: disposeBag)
    }
    
    private func loadUser(with username: String) {
        isLoadingUser.onNext(true)
        twitterDataSource.getUser(username: username)
            .observeOn(MainScheduler.instance)
            .subscribe { [weak self] event in
                self?.isLoadingUser.onNext(false)
                switch event {
                case Event.next(let twitterUser):
                    self?.delegate?.didLoadUser(user: twitterUser)
                case Event.error:
                    self?.couldNotLoadUser.onNext(())
                default: break
                }
            }
            .disposed(by: disposeBag)
    }
    
}
