//
//  RequestProtocol.swift
//  TwitterAnalyzer
//
//  Created by Bruno Vieira on 21/06/19.
//  Copyright © 2019 Bruno Vieira. All rights reserved.
//

import Foundation

enum HttpMethod: String {
    case GET
    case POST
}

protocol RequestProtocol {
    var endpoint: String { get }
    var method: HttpMethod { get }
    var headerFields: [String : String]? { get }
    var body: [String : Any]? { get }
}
