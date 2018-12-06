//
//  RestService.swift
//  ifood-mobile-test
//
//  Created by Marcio Garcia on 30/11/18.
//  Copyright © 2018 oxltech.com. All rights reserved.
//

import Foundation

enum RequestParameterEncoding: String {
    case json = "JSon"
    case queryString = "QueryString"
}

protocol RestService {
    
    func defaultHeader() -> [String: String]?
    
    func get(_ url: String,
             parameters: [String: Any]?,
             encoding: RequestParameterEncoding,
             headers: [String: String]?,
             success: @escaping (Data?) -> (),
             failure: @escaping (NSError) -> ())
    
    func post(_ url: String,
              parameters: [String: Any]?,
              encoding: RequestParameterEncoding,
              headers: [String: String]?,
              success: @escaping (Data?) -> (),
              failure: @escaping (NSError) -> ())
    
    func put(_ url: String,
             parameters: [String: Any]?,
             encoding: RequestParameterEncoding,
             headers: [String: String]?,
             success: @escaping (Data?) -> (),
             failure: @escaping (NSError) -> ())
    
    func delete(_ url: String,
                parameters: [String: Any]?,
                encoding: RequestParameterEncoding,
                headers: [String: String]?,
                success: @escaping (Data?) -> (),
                failure: @escaping (NSError) -> ())
}
