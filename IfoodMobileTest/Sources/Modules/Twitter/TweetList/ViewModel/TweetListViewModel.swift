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
}

protocol TweetListViewModelInput {}

final class TweetListViewModel: TweetListViewModelOutput, TweetListViewModelInput {
    
    var tweets: BehaviorSubject<[TweetModel]>
    
    init(tweets: [TweetModel]) {
        self.tweets = BehaviorSubject<[TweetModel]>(value: tweets)
    }
}
