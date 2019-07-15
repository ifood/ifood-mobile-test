//
//  CustomError+Extensions.swift
//  twitter-mood
//
//  Created by Dennis Merli on 7/7/19.
//  Copyright © 2019 Dennis Merli. All rights reserved.
//

import Foundation
import Moya

extension CustomError {
    
    class func errorFromService(with moyaResponse: Response) -> NSError {
        let domain = moyaResponse.request?.url?.absoluteString
        let status = moyaResponse.statusCode
        let message = NetworkResponseStatus.fromStatusCode(status: status).message
        let networkError = NSError(domain: domain ?? CustomError.errorDomain,
                                   code: status,
                                   userInfo: [NSLocalizedDescriptionKey: message ])
        return networkError
    }
}

enum NetworkResponseStatus {
    
    case notFound
    case forbidden
    case unauthorized
    case internalServerError
    case unknown
    
    var message: String {
        switch self {
        case .notFound:
            return BaseLocalizable.errorNotFound
        case .forbidden:
            return BaseLocalizable.errorForbidden
        case .unauthorized:
            return BaseLocalizable.errorUnauthorized
        case .internalServerError:
            return BaseLocalizable.errorInternalServer
        case .unknown:
            return BaseLocalizable.unknownError
        }
    }
    
    var status: Int? {
        switch self {
        case .notFound:
            return 404
        case .forbidden:
            return 403
        case .unauthorized:
            return 401
        case .internalServerError:
            return 500
        default:
            return nil
        }
    }
    
    static func fromStatusCode(status: Int?) -> NetworkResponseStatus {
        guard let status = status else {
            return .unknown
        }
        switch status {
        case 404:
            return .notFound
        case 403:
            return .forbidden
        case 401:
            return .unauthorized
        case 500:
            return .internalServerError
        default:
            return .unknown
        }
    }
}

