//
//  Tweets.swift
//  Movile-Test
//
//  Created by Thiago Felipe Alves on 14/07/19.
//  Copyright © 2019 Thalves. All rights reserved.
//

import UIKit

struct Tweets: THCodable {

    var text: String

    enum CodingKeys: String, CodingKey {
        case text = "full_text"
    }
}
