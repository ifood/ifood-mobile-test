//
//  Tweet.swift
//  my-tweets
//
//  Created by Gabriel Catice on 22/11/18.
//  Copyright © 2018 Gabriel Catice. All rights reserved.
//

import Foundation

public struct Tweet {
    public let id: String
    public let sentence: Sentence?
    public let createdDate: String?
    
    public init(id: String, sentence: Sentence?, createdDate: String?) {
        self.id = id
        self.sentence = sentence
        self.createdDate = createdDate
    }
}
