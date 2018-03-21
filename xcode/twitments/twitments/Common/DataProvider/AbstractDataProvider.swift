//
//  AbstractDataProvider.swift
//  twitments
//
//  Created by Ezequiel on 08/03/18.
//  Copyright © 2018 Ezequiel. All rights reserved.
//

import Foundation

protocol AbstractDataProvider: class {
    func errorData(_ provider: AbstractDataProvider?, error: NSError)
}

class AbstractDataProviderManager<T, S> {
    let dataError = NSError(domain: "data error", code: 0, userInfo: nil)
    var dataProvider: T?
    var viewModel: S?
}
