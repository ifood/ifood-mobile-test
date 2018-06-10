//
//  Tweet.swift
//  iFoodTest
//
//  Created by Mauricio Cesar Maniglia Junior on 08/06/18.
//  Copyright © 2018 Mauricio Cesar Maniglia Junior. All rights reserved.
//

import Foundation

struct Tweet {
    let idStr: String
    let user: TwitterUser
    let text: String
}

extension Tweet: Parsable {
    
    private struct Constants {
        static let idStrKey = "id_str"
        static let userKey = "user"
        static let textKey = "text"
        static let createdAtKey = "created_at"
    }
    
    static func fromJSON(json: [String: Any]) -> Tweet? {
        
        if let idString = json[Constants.idStrKey] as? String,
            let userJson = json[Constants.userKey] as? [String: Any],
            let user = TwitterUser.fromJSON(json: userJson),
            let text = json[Constants.textKey] as? String {
            
            return Tweet(idStr: idString, user: user, text: text)
        }
        
        return nil
    }
}
