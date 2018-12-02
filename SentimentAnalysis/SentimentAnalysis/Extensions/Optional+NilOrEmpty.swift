//
//  Optional+NilOrEmpty.swift
//  SentimentAnalysis
//
//  Created by Thales Frigo on 02/12/18.
//  Copyright © 2018 Thales Frigo. All rights reserved.
//

import Foundation

extension Optional where Wrapped: Collection {
    
    var isNilOrEmpty: Bool {
        return self?.isEmpty ?? true
    }
}
