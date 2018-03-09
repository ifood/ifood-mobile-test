//
//  ShowSentimentDataProvider.swift
//  twitments
//
//  Created by Ezequiel on 08/03/18.
//  Copyright © 2018 Ezequiel. All rights reserved.
//

import Foundation

protocol ShowSentimentDataProvider: AbstractDataProvider {
    func reloadData(_ provider: AbstractDataProvider?, viewModels: TwitterSentimentViewModel)
}

class ShowSentimentDataProviderManager: AbstractDataProviderManager<ShowSentimentDataProvider, TwitterSentimentViewModel> {
}
