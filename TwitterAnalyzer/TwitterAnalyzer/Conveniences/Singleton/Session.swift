//
//  Session.swift
//  TwitterAnalyzer
//
//  Created by Bruno Vieira on 19/06/19.
//  Copyright © 2019 Bruno Vieira. All rights reserved.
//

import Foundation

protocol SessionProtocol {
    var twitterAuthenticatedToken: String? { get set }
    static var shared: SessionProtocol { get }
}

class Session: SessionProtocol {
    var twitterAuthenticatedToken: String?
    
    // MARK: - Singleton
    static var shared: SessionProtocol = Session()
}
