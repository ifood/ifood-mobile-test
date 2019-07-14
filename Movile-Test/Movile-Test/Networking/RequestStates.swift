//
//  RequestStates.swift
//  Movile-Test
//
//  Created by Thiago Felipe Alves on 14/07/19.
//  Copyright © 2019 Thalves. All rights reserved.
//

import Foundation

enum RequestStates<T> {
    case loading
    case errored(error: NSError)
    case load(data: T)
}
