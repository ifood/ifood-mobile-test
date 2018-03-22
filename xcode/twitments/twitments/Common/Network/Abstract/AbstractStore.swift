//
//  AbstractClient.swift
//  twitments
//
//  Created by Ezequiel on 08/03/18.
//  Copyright © 2018 Ezequiel. All rights reserved.
//

import Alamofire
import Foundation
import ObjectMapper

protocol AbstractStore {
    var genericError: NSError { get set }
    typealias Completion<T> = (_ result: T, _ failure: NSError?) -> Void
}

class AbstractAPIStore {
    var genericError = NSError(domain: "Erro ao obter informações", code: 900, userInfo: [NSLocalizedDescriptionKey: "Erro ao obter as informações"])
}

enum AbstractStoreError: Error {
    case FoundNil(String)
}
