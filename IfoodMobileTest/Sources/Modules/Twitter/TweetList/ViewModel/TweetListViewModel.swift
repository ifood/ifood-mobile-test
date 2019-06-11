//
//  TweetListViewModel.swift
//  IfoodMobileTest
//
//  Created by André Vieira on 10/06/19.
//  Copyright © 2019 André Vieira. All rights reserved.
//

import Foundation
import RxSwift

protocol TweetListViewModelOutput {
    var tweets: BehaviorSubject<[TweetModel]> { get }
    var title: String { get }
}

protocol TweetListViewModelInput {}

final class TweetListViewModel: TweetListViewModelOutput, TweetListViewModelInput {
    
    var tweets = BehaviorSubject<[TweetModel]>(value: [])
    var title: String {
        return "Tweets"
    }
    
    init(tweets: [TweetModel]) {
        self.tweets.onNext(tweets)
    }
}
