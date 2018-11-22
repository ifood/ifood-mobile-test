//
//  Sentence.swift
//  my-tweets
//
//  Created by Gabriel Catice on 22/11/18.
//  Copyright © 2018 Gabriel Catice. All rights reserved.
//

import Foundation

public struct Sentence {
    public let text: String?
    public let score: Double?
    
    public init(text: String?, score: Double?) {
        self.text = text
        self.score = score
    }
}
