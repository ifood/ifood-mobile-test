//
//  Endpoint.swift
//  Movile-Test
//
//  Created by Thiago Felipe Alves on 14/07/19.
//  Copyright © 2019 Thalves. All rights reserved.
//

import Foundation

public class Endpoint {
    /// A tuple that recieves an URI and the http request method
    typealias EndpointType = (uri: String, method: String)

    /// Contains the http method String simplified for
    struct HTTPMethod {
        static let get = "GET"
        static let post = "POST"
        static let update = "PUT"
        static let delete = "DELETE"
        static let head = "HEAD"
    }

    // Endpoints list
    struct Home {
        static let list: EndpointType = (uri: "", method: HTTPMethod.get)
    }
}
