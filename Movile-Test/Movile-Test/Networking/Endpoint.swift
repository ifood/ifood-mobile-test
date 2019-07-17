//
//  Endpoint.swift
//  Movile-Test
//
//  Created by Thiago Felipe Alves on 14/07/19.
//  Copyright © 2019 Thalves. All rights reserved.
//

import Foundation

enum Domain {
    case Twitter
    case Google
}

public class Endpoint {
    /// A tuple that recieves an URI and the http request method
    typealias EndpointType = (domain: Domain, uri: String, method: String)

    /// Contains the http method String simplified for
    struct HTTPMethod {
        static let get = "GET"
        static let post = "POST"
        static let update = "PUT"
        static let delete = "DELETE"
        static let head = "HEAD"
    }

    // Endpoints list
    struct Twitter {
        static let Authentication: EndpointType = (domain: .Twitter, uri: "oauth2/token?grant_type=client_credentials", method: HTTPMethod.post)
        static let List: EndpointType = (domain: .Twitter, uri: "1.1/statuses/user_timeline.json?screen_name=realDonaldTrump&tweet_mode=extended", method: HTTPMethod.get)
    }
}
