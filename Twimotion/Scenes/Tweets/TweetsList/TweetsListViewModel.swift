//
//  TweetsListViewModel.swift
//  Twimotion
//
//  Created by Antony on 26/05/18.
//  Copyright © 2018 Twimotion. All rights reserved.
//

import Foundation
import RxSwift
import RxDataSources

typealias TweetsListSectionViewModel = SectionModel<String, TweetItemViewModel>

protocol TweetsListViewModelType {
    //inputs
    var selectTweetEvent: PublishSubject<Int> { get }
    var retryLoadTweets: PublishSubject<Void> { get }

    // outputs
    var username: Observable<String> { get }
    var couldNotLoadTweets: BehaviorSubject<Bool> { get }
    var isLoadingTweets: BehaviorSubject<Bool> { get }
    var cellViewModels: Observable<[TweetsListSectionViewModel]> { get }
}

protocol TweetsListViewModelDelegate: class {
    func didSelectTweet(_ tweet: Tweet)
}

class TweetsListViewModel: TweetsListViewModelType {

    weak var delegate: TweetsListViewModelDelegate?

    // MARK: - Private properties
    private let twitterDataSource: TwitterDataSourceType
    private let twitterUser: Variable<TwitterUser>

    // MARK: - Rx
    var disposeBag = DisposeBag()

    /// Inputs
    var selectTweetEvent = PublishSubject<Int>()
    var retryLoadTweets = PublishSubject<Void>()

    /// Outputs

    var username: Observable<String> {
        return twitterUser.asObservable().map { $0.screenName }
    }

    let couldNotLoadTweets = BehaviorSubject<Bool>(value: false)

    let isLoadingTweets = BehaviorSubject<Bool>(value: false)

    var cellViewModels: Observable<[TweetsListSectionViewModel]> {
        return tweets.asObservable().map {
            let vms = $0.map(TweetItemViewModel.init)
            let section = TweetsListSectionViewModel(model: L10n.Tweets.tweetsSectionMessage, items: vms)
            return [section]
        }
    }

    //private
    private let tweets = Variable<[Tweet]>([])

    init(twitterUser: TwitterUser, twitterDataSource: TwitterDataSourceType) {
        self.twitterUser = Variable(twitterUser)
        self.twitterDataSource = twitterDataSource

        retryLoadTweets
            .bind { [weak self] in self?.loadLastestTweets() }
            .disposed(by: disposeBag)

        // when select new tweet should notify delegate
        selectTweetEvent
            .flatMap { [weak tweets] index -> Observable<Tweet> in
                guard let strongTweets = tweets,
                    strongTweets.value.count >= index else {
                    fatalError("element not found")
                }
                return Observable.just(strongTweets.value[index])
            }
            .bind { [weak self] tweet in
                self?.delegate?.didSelectTweet(tweet)
            }.disposed(by: disposeBag)

        // load sentiment for current Tweet
        loadLastestTweets()
    }

}

extension TweetsListViewModel {

    private func loadLastestTweets() {
        isLoadingTweets.onNext(true)
        couldNotLoadTweets.onNext(false)

        twitterUser.asObservable()
            .flatMapLatest(twitterDataSource.getLatestTweets)
            .observeOn(MainScheduler.instance)
            .subscribe { [weak self] event in
                self?.isLoadingTweets.onNext(false)

                switch event {
                case .next(let tweets):
                    self?.tweets.value = tweets
                case .error:
                    self?.couldNotLoadTweets.onNext(true)
                default: break
                }
            }
            .disposed(by: disposeBag)
    }

}
