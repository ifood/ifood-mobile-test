//
//  TwitterToken.swift
//  Movile-Test
//
//  Created by Thiago Felipe Alves on 16/07/19.
//  Copyright © 2019 Thalves. All rights reserved.
//

import UIKit

struct TwitterToken: THCodable {

    var type: String
    var token: String

    enum CodingKeys: String, CodingKey {
        case type = "token_type"
        case token = "access_token"
    }
}
