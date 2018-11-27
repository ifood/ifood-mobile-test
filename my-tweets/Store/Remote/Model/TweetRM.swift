//
//  TweetRM.swift
//  my-tweets
//
//  Created by Gabriel Catice on 27/11/18.
//  Copyright © 2018 Gabriel Catice. All rights reserved.
//

import Foundation

struct TweetRM: Codable {
    public let id: String
    public let sentence: String?
    public let createdDate: String?
    
    enum CodingKeys: String, CodingKey {
        case id = "id_str"
        case sentence = "text"
        case createdDate = "created_at"
    }
}
