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
    var error: NSError { get set }
    typealias completion<T> = (_ result: T, _ failure: NSError?) -> Void
}

class AbstractAPIStore {
    var error = NSError(domain: "", code: 900, userInfo: [NSLocalizedDescriptionKey: "Erro ao obter as informações"])
}
