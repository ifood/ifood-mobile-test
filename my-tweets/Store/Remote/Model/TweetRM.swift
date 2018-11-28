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
    public let user: UserRM
    
    enum CodingKeys: String, CodingKey {
        case id = "id_str"
        case sentence = "text"
        case createdDate = "created_at"
        case user = "user"
    }
}

extension TweetRM {
    func toDomainModel() -> Tweet {
        return Tweet(id: id, sentence: Sentence(text: sentence, score: nil), createdDate: createdDate, user: user.toDomainModel())
    }
}

extension Array where Element == TweetRM {
    func toDomainModel() -> [Tweet] {
        return map {$0.toDomainModel() }
    }
}
