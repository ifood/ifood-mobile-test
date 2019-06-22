//
//  TwitterAccountRequest.swift
//  TwitterAnalyzer
//
//  Created by Bruno Vieira on 19/06/19.
//  Copyright © 2019 Bruno Vieira. All rights reserved.
//

import UIKit

struct TwitterAccountRequest: RequestProtocol {

    let accessToken: String
    let screenName: String
    
    var endpoint: String {
        return "https://api.twitter.com/1.1/users/show.json?screen_name=\(screenName)"
    }
    
    var method: HttpMethod {
        return HttpMethod.GET
    }
    
    var headerFields: [String : String]? {
        return ["Authorization": "Bearer \(accessToken)"]
    }
    
    var body: [String : Any]? {
        return nil
    }
}
