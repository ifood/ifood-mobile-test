//
//  JSONDecoder+Ext.swift
//  Twimotion
//
//  Created by Antony on 26/05/18.
//  Copyright © 2018 Twimotion. All rights reserved.
//

import Foundation

public extension JSONDecoder {
    public static var sharedDecoder: JSONDecoder {
        let decoder = JSONDecoder()
        decoder.keyDecodingStrategy = .convertFromSnakeCase
        decoder.dateDecodingStrategy = .formatted(DateFormatter.E_MMM_d_HHmmss_Z_yyyy)
        return decoder
    }
}
