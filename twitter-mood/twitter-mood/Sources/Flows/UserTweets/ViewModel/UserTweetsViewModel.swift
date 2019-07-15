//
//  UserTweetsViewModel.swift
//  twitter-mood
//
//  Created by Dennis Merli on 7/7/19.
//  Copyright © 2019 Dennis Merli. All rights reserved.
//

import Foundation
import RxSwift

internal protocol UserTweetsViewModelOutput {
    var twitterService: TwitterServiceType { get }
    var username: String { get }
    var isLoading: BehaviorSubject<Bool> { get }
    var errorObservable: PublishSubject<Error?> { get }
    var userTweets: BehaviorSubject<[UserTweetCellViewModel]> { get }
}

internal protocol UserTweetsViewModelInput {
    func loadUserTweets()
}

typealias UserTweetsViewModelType = UserTweetsViewModelOutput & UserTweetsViewModelInput

class UserTweetsViewModel: BaseViewModel, UserTweetsViewModelType {
    var twitterService: TwitterServiceType
    var username: String
    var isLoading: BehaviorSubject<Bool>
    var errorObservable: PublishSubject<Error?>
    var userTweets: BehaviorSubject<[UserTweetCellViewModel]>
    
    init(username: String, twitterService: TwitterServiceType) {
        self.username = username
        self.twitterService = twitterService
        self.isLoading = BehaviorSubject<Bool>(value: false)
        self.errorObservable = PublishSubject<Error?>()
        self.userTweets = BehaviorSubject<[UserTweetCellViewModel]>(value: [])
    }
    
    func loadUserTweets() {
        isLoading.onNext(true)
        twitterService.fetchTweets(username: username, count: 10)
            .map({ $0.map({ UserTweetCellViewModel(tweet: $0)}) })
            .subscribe(onSuccess: { [weak self] (cellViewModels) in
                self?.isLoading.onNext(false)
                self?.userTweets.onNext(cellViewModels)
            }, onError: { (error) in
                self.isLoading.onNext(false)
                self.errorObservable.onNext(error)
            }).disposed(by: disposeBag)
    }
}
