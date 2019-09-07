//
//  TwitterToken.swift
//  ifood-mobile-test
//
//  Created by Jean Vinge on 26/12/18.
//  Copyright © 2018 Jean Vinge. All rights reserved.
//

import Foundation

public struct TwitterToken: Codable {

    // MARK: Var

    enum CodingKeys: String, CodingKey {
        case tokenType = "token_type"
        case accessToken = "access_token"
    }

    public let tokenType: String
    public let accessToken: String
}
