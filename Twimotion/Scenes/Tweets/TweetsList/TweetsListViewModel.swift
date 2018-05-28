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
    // outputs
    var username: Observable<String> { get }
    var couldNotLoadTweets: PublishSubject<Void> { get }
    var isLoadingTweets: BehaviorSubject<Bool> { get }
    var cellViewModels: Observable<[TweetsListSectionViewModel]> { get }
}

class TweetsListViewModel: TweetsListViewModelType {

    // MARK: - Private properties
    private let twitterDataSource: TwitterDataSourceType
    private let twitterUser: Variable<TwitterUser>

    // MARK: - Rx
    var disposeBag = DisposeBag()

    //outputs

    var username: Observable<String> {
        return twitterUser.asObservable().map { $0.screenName }
    }

    let couldNotLoadTweets = PublishSubject<Void>()

    let isLoadingTweets = BehaviorSubject<Bool>(value: false)

    var cellViewModels: Observable<[TweetsListSectionViewModel]> {
        return tweets.asObservable().map {
            let vms = $0.map(TweetItemViewModel.init)
            let section = TweetsListSectionViewModel(model: "", items: vms)
            return [section]
        }
    }

    //private
    private let tweets = Variable<[Tweet]>([])

    init(twitterUser: TwitterUser, twitterDataSource: TwitterDataSourceType) {
        self.twitterUser = Variable(twitterUser)
        self.twitterDataSource = twitterDataSource

        loadLastestTweets()
    }

}

extension TweetsListViewModel {
    private func loadLastestTweets() {
        isLoadingTweets.onNext(true)
        twitterUser.asObservable()
            .flatMapLatest(twitterDataSource.getLatestTweets)
            .observeOn(MainScheduler.instance)
            .subscribe { [weak self] event in
                self?.isLoadingTweets.onNext(false)

                switch event {
                case .next(let tweets):
                    self?.tweets.value = tweets
                case .error:
                    self?.couldNotLoadTweets.onNext(())
                default: break
                }
            }
            .disposed(by: disposeBag)
    }
}
