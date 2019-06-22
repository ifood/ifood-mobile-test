//
//  TwitterAuthenticationRequest.swift
//  TwitterAnalyzer
//
//  Created by Bruno Vieira on 19/06/19.
//  Copyright © 2019 Bruno Vieira. All rights reserved.
//

import UIKit

struct TwitterAuthenticationRequest: RequestProtocol {
    
    let twitterApiKey: String
    let twitterApiSecretKey: String
    
    var endpoint: String {
        return "https://api.twitter.com/oauth2/token?grant_type=client_credentials"
    }
    
    var method: HttpMethod {
        return HttpMethod.POST
    }
    
    var headerFields: [String : String]? {
        let twitterToken = "\(twitterApiKey):\(twitterApiSecretKey)".data(using: .utf8)?.base64EncodedString() ?? ""
        return ["Authorization": "Basic \(twitterToken)",
            "Content-Type": "application/x-www-form-urlencoded;charset=UTF-8"]
    }
    
    var body: [String : Any]? {
        return nil
    }
}
