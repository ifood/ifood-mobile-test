//
//  CustomError.swift
//  twitter-mood
//
//  Created by Dennis Merli on 7/7/19.
//  Copyright © 2019 Dennis Merli. All rights reserved.
//

import Foundation

class CustomError {
    
    class var errorDomain: String {
        guard let bundleId = Bundle.main.bundleIdentifier else { return "" }
        return bundleId
    }
    
    class func unknown() -> NSError {
        return NSError(domain: CustomError.errorDomain,
                       code: 15,
                       userInfo: [NSLocalizedDescriptionKey: BaseLocalizable.defaultErrorDescription])
    }
    
    class func cannotParse() -> NSError {
        return NSError(domain: CustomError.errorDomain, code: 16, userInfo: [NSLocalizedDescriptionKey: BaseLocalizable.cannotParse])
    }
    
    class func networkError(errorCode: Int) -> NSError {
        return NSError(domain: CustomError.errorDomain, code: errorCode, userInfo: [NSLocalizedDescriptionKey: BaseLocalizable.networkError])
    }
    
    class func nilParameter(parameter: String) -> NSError {
        return NSError(domain: CustomError.errorDomain, code: 34, userInfo: [NSLocalizedDescriptionKey: BaseLocalizable.nilParameter(parameter)])
    }
    
    class func otherError(reason: String) -> NSError {
        return NSError(domain: CustomError.errorDomain, code: 48, userInfo: [NSLocalizedDescriptionKey: reason])
    }
}

extension NSError {
    func isUserRelevant() -> Bool {
        return self.code > 100
    }
}

