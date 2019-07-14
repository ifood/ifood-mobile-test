//
//  API.swift
//  Movile-Test
//
//  Created by Thiago Felipe Alves on 14/07/19.
//  Copyright © 2019 Thalves. All rights reserved.
//

import Foundation

class API {
    enum Environment: String {

        case production = ""

        //Just to be more readable
        func getValue() -> String {
            return self.rawValue
        }
    }

    let environment: Environment

    init() {
        environment = Environment.production
    }

    lazy var homeServices = HomeAPI()
}
