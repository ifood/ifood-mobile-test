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

        case twitter = "https://api.twitter.com/"
        case google = "https://language.googleapis.com/v1"

        //Just to be more readable
        func getValue() -> String {
            return self.rawValue
        }
    }

    let environment: Environment

    init() {
        environment = Environment.twitter
    }

    lazy var homeServices = HomeAPI()
    lazy var sentimentServices = SentimentAPI()
}
