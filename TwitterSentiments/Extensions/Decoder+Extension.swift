//
//  Decoder+Extension.swift
//  TwitterSentiments
//
//  Created by Marina Azevedo on 14/07/19.
//  Copyright © 2019 Marina Azevedo. All rights reserved.
//

import Foundation

public extension JSONDecoder {
    static var sharedDecoder: JSONDecoder {
        let decoder = JSONDecoder()
        decoder.keyDecodingStrategy = .convertFromSnakeCase
        decoder.dateDecodingStrategy = .formatted(DateFormatter.E_MMM_d_HHmmss_Z_yyyy)
        return decoder
    }
}
