//
//  Parsable.swift
//  iFoodTest
//
//  Created by Mauricio Cesar Maniglia Junior on 09/06/18.
//  Copyright © 2018 Mauricio Cesar Maniglia Junior. All rights reserved.
//

import Foundation

protocol Parsable {
    associatedtype T    
    static func fromJSON(json: [String: Any]) -> T?
}
