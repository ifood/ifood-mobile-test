//
//  TweetSentimentViewModel.swift
//  Twimotion
//
//  Created by Antony on 29/05/18.
//  Copyright © 2018 Twimotion. All rights reserved.
//

import Foundation
import UIKit
import RxSwift

protocol TweetSentimentViewModelType {
    // inputs
    var dismissEvent: PublishSubject<Void> { get }
    var loadSentiment: PublishSubject<Void> { get }

    //outputs
    var tweetDescription: Observable<String> { get }
    var sentimentEmoji: Observable<String> { get }
    var sentimentDescription: Observable<String> { get }
    var sentimentColor: Observable<UIColor> { get }

    var isLoadingSentiment: BehaviorSubject<Bool> { get }
    var errorLoadingSentiment: BehaviorSubject<Bool> { get }
}

protocol TweetSentimentViewModelDelegate: class {
    func didDismissedPopup()
}

class TweetSentimentViewModel: TweetSentimentViewModelType {

    var disposeBag = DisposeBag()

    weak var delegate: TweetSentimentViewModelDelegate?

    // MARK: - RxBindings
    //inputs
    let dismissEvent = PublishSubject<Void>()
    let loadSentiment = PublishSubject<Void>()

    // outputs
    let isLoadingSentiment = BehaviorSubject<Bool>(value: false)
    let errorLoadingSentiment = BehaviorSubject<Bool>(value: false)

    var tweetDescription: Observable<String> {
        return tweet.asObservable()
            .map { "\"\($0.text)\" - @\($0.user.screenName)" }
    }

    var sentimentEmoji: Observable<String> {
        return sentiment.asObservable().map {
            switch $0 {
            case .happy: return "😃"
            case .neutral: return "😐"
            case .sad: return "😔"
            }
        }
    }

    var sentimentDescription: Observable<String> {
        return sentiment.asObservable().map {
            switch $0 {
            case .happy: return L10n.TweetSentiment.happyMessage
            case .neutral: return L10n.TweetSentiment.neutralMessage
            case .sad: return L10n.TweetSentiment.sadMessage
            }
        }
    }

    var sentimentColor: Observable<UIColor> {
        return sentiment.asObservable().map {
            switch $0 {
            case .happy: return Styles.colors.happySentiment
            case .neutral: return Styles.colors.neutralSentiment
            case .sad: return Styles.colors.sadSentiment
            }
        }
    }

    // MARK: - Private
    private var naturalLangDataSource: NaturalLangDataSourceType
    private let tweet: Variable<Tweet>
    private let sentiment = PublishSubject<Sentiment>()

    // MARK: - Initializers

    init(tweet: Tweet, naturalLangDataSource: NaturalLangDataSourceType) {
        self.tweet = Variable(tweet)
        self.naturalLangDataSource = naturalLangDataSource

        // dismiss event
        dismissEvent
            .bind(onNext: { [weak self] in self?.delegate?.didDismissedPopup() })
            .disposed(by: disposeBag)

        loadSentiment
            .bind { [weak self] in self?.loadTweetSentiment() }
            .disposed(by: disposeBag)
    }

}

extension TweetSentimentViewModel {

    private func loadTweetSentiment() {
        isLoadingSentiment.onNext(true)

        tweet.asObservable()
            .map { $0.text }
            .flatMapLatest(naturalLangDataSource.getSentiment)
            .observeOn(MainScheduler.instance)
            .subscribe { [weak self] event in
                self?.isLoadingSentiment.onNext(false)

                switch event {
                case .next(let sent): self?.sentiment.onNext(sent)
                case .error: self?.errorLoadingSentiment.onNext(true)
                default: break
                }
            }.disposed(by: disposeBag)
    }

}
