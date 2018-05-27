//
//  TweetsListViewModel.swift
//  Twimotion
//
//  Created by Antony on 26/05/18.
//  Copyright © 2018 Twimotion. All rights reserved.
//

import Foundation
import RxSwift

protocol TweetsListViewModelType {

}

struct TweetsListViewModel: TweetsListViewModelType {

    // MARK: - Private properties
    private let twitterDataSource: TwitterDataSourceType

    // MARK: - Rx
    private let tweets = Variable<[Tweet]>([])

    init(twitterDataSource: TwitterDataSourceType) {
        self.twitterDataSource = twitterDataSource
    }

}
