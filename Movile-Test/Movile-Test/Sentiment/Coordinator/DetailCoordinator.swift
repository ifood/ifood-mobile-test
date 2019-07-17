//
//  DetailCoordinator.swift
//  Movile-Test
//
//  Created by Thiago Felipe Alves on 17/07/19.
//  Copyright © 2019 Thalves. All rights reserved.
//

import UIKit

class DetailCoordinator {

    // MARK: - Properties
    var view: DetailVC?
    var navigation: UINavigationController?

    var tweet: Tweet

    // MARK: - Initializers
    init(tweet: Tweet) {
        self.tweet = tweet
    }

    func start() -> DetailVC {
        let viewModel = DetailVM(tweet: self.tweet)
        let viewController = DetailVC(viewModel: viewModel)
        self.view = viewController
        return viewController
    }
}
