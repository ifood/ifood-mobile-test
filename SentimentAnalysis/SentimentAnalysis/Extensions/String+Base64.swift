//
//  String+Base64.swift
//  SentimentAnalysis
//
//  Created by Thales Frigo on 27/11/18.
//  Copyright © 2018 Thales Frigo. All rights reserved.
//

import Foundation

extension String {
    
    var base64Encode: String? {
        let data = self.data(using: String.Encoding.utf8, allowLossyConversion: true)
        if let base64Encoded = data?.base64EncodedString() {
            return base64Encoded
        }
        return nil
    }
}
