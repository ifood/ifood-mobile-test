//
//  MoyaEndpoint.swift
//  my-tweets
//
//  Created by Gabriel Catice on 22/11/18.
//  Copyright © 2018 Gabriel Catice. All rights reserved.
//

import Moya

struct MoyaEndpoint {
    static let env: [String: Any] = Bundle.main.infoDictionary!
    
    /**
     Decide which header to use on a request
     
     Primary token:
     - User authorization token (when user is logged in)
     - App token (otherwise)
     Secondary token:
     - App token (when user is logged in)
     - N/A (otherwise)
     */
    
    static let headerClosure = { (target: GoogleProvider) -> Endpoint in
        let defaultEndpoint = MoyaProvider.defaultEndpointMapping(for: target)
        return defaultEndpoint.adding(newHTTPHeaderFields: [
            "Content-type": "application/json"
            ])
    }
}
