//
//  NetworkDispatcherStup.swift
//  TwitterAnalyzerTests
//
//  Created by Bruno Vieira on 21/06/19.
//  Copyright © 2019 Bruno Vieira. All rights reserved.
//

import Foundation
@testable import TwitterAnalyzer

class NetworkDispatcherStub: NetworkDispatcherProtocol {
    func requestArray<T>(of type: T.Type, request: RequestProtocol, completion: @escaping (([T]?, NetworkError?) -> Void)) where T : Decodable, T : Encodable {
        
    }
    
    func requestObject<T>(of type: T.Type, request: RequestProtocol, completion: @escaping ((T?, NetworkError?) -> Void)) where T : Decodable, T : Encodable {
        
    }
    
    func requestImage(from urlString: String, completion: @escaping ((Data?, NetworkError?) -> Void)) {
        
    }
}
