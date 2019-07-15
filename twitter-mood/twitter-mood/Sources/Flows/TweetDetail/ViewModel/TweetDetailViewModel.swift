//
//  TweetDetailViewModel.swift
//  twitter-mood
//
//  Created by Dennis Merli on 7/7/19.
//  Copyright © 2019 Dennis Merli. All rights reserved.
//

import Foundation
import RxSwift

internal protocol TweetDetailViewModelOutput {
    var tweetMood: BehaviorSubject<TweetMoodViewModel?> { get }
    var isLoading: BehaviorSubject<Bool> { get }
    var errorObservable: PublishSubject<Error?> { get }
}

internal protocol TweetDetailViewModelInput {
    var tweetCellViewModel: UserTweetCellViewModel { get }
    var googleService: GoogleService { get }
    func loadMoodFromTweet()
}

typealias TweetDetailViewModelType = TweetDetailViewModelOutput  & TweetDetailViewModelInput

class TweetDetailViewModel: BaseViewModel, TweetDetailViewModelType {
    
    var tweetMood: BehaviorSubject<TweetMoodViewModel?>
    
    var isLoading: BehaviorSubject<Bool>
    
    var errorObservable: PublishSubject<Error?>
    
    var tweetCellViewModel: UserTweetCellViewModel
    
    var googleService: GoogleService
    
    var tweetContent: String {
        return tweetCellViewModel.tweetContent
    }
    
    init(tweetCellViewModel: UserTweetCellViewModel, googleService: GoogleService) {
        self.tweetCellViewModel = tweetCellViewModel
        self.googleService = googleService
        self.errorObservable = PublishSubject<Error?>()
        self.isLoading = BehaviorSubject<Bool>(value: false)
        self.tweetMood = BehaviorSubject<TweetMoodViewModel?>(value: nil)
    }
    
    func loadMoodFromTweet() {
        isLoading.onNext(true)
        googleService.moodForTweet(tweetCellViewModel.tweetContent)
            .map({ TweetMoodViewModel(tweetMood: $0) })
            .subscribe(onSuccess: { [weak self] (mood) in
                self?.isLoading.onNext(false)
                self?.tweetMood.onNext(mood)
            }, onError: { (error) in
                self.isLoading.onNext(false)
                self.errorObservable.onNext(error)
            }).disposed(by: disposeBag)
    }
    
}
