//
//  TwitterUser.swift
//  iFoodTest
//
//  Created by Mauricio Cesar Maniglia Junior on 09/06/18.
//  Copyright © 2018 Mauricio Cesar Maniglia Junior. All rights reserved.
//

import Foundation

struct TwitterUser {
    let idStr: String
    let name: String
    let screenName: String
    let profileImageUrl: URL
}

extension TwitterUser: Parsable {
    
    private struct Constants {
        static let idStrKey = "id_str"
        static let nameKey = "name"
        static let screenNameKey = "screen_name"
        static let profileImageUrlKey = "profile_image_url_https"
    }
    
    static func fromJSON(json: [String: Any]) -> TwitterUser? {
        
        if let idString = json[Constants.idStrKey] as? String,
            let name = json[Constants.nameKey] as? String,
            let screenName = json[Constants.screenNameKey] as? String,
            let profileImageUrlStr = json[Constants.profileImageUrlKey] as? String,
            let profileImageUrl = URL(string: profileImageUrlStr) {
            
            return TwitterUser(idStr: idString, name: name, screenName: screenName, profileImageUrl: profileImageUrl)
        }
        
        return nil
    }
}
