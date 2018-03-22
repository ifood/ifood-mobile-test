//
//  AddHandlerDataProvider.swift
//  twitments
//
//  Created by Ezequiel on 08/03/18.
//  Copyright © 2018 Ezequiel. All rights reserved.
//

import Foundation

protocol AddHandlerDataProvider: AbstractDataProvider {
    func reloadData(_ provider: AbstractDataProvider?, viewModels: [TwitterResultViewModel])
    func reloadData(_ provider: AbstractDataProvider?, authentication: Bool)
}

class AddHandlerDataProviderManager: AbstractDataProviderManager<AddHandlerDataProvider, TwitterResultViewModel> {

    func fetchUserTwittes(_ userName: String) {
        TwitterAPIStore().userTimeline(userName) { [weak self] (result, error) in
            guard let _error = error else {
                let viewModels = result.flatMap { TwitterResultViewModel(model: $0) }
                self?.dataProvider?.reloadData(self?.dataProvider, viewModels: viewModels)
                return
            }
            self?.dataProvider?.errorData(self?.dataProvider, error: _error)
        }
    }

    func authenticate() {
        TwitterAPIStore().authentication { (authentication, error) in
            guard let _error = error else {
                self.dataProvider?.reloadData(self.dataProvider, authentication: authentication)
                return
            }
            self.dataProvider?.errorData(self.dataProvider, error: _error)
        }
    }
}
