//
//  Requester.swift
//  twitments
//
//  Created by Ezequiel on 08/03/18.
//  Copyright © 2018 Ezequiel. All rights reserved.
//

import Alamofire
import Foundation

public class Requester {
    
    // MARK: Shared Instance
    static let shared: Requester = {
        let instance = Requester()
        return instance
    }()
    
    let cookies = HTTPCookieStorage.shared
    var alamofire: Alamofire.SessionManager!
    
    func setupAlamofire() {
        let configuration = URLSessionConfiguration.default
        configuration.timeoutIntervalForRequest = 30
        configuration.timeoutIntervalForResource = 60
        configuration.httpCookieAcceptPolicy = .always
        configuration.httpCookieStorage = cookies
        alamofire = Alamofire.SessionManager(configuration: configuration)
    }
}
