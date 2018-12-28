//
//  TwitterError.swift
//  Domain
//
//  Created by Jean Vinge on 27/12/18.
//  Copyright © 2018 Jean Vinge. All rights reserved.
//

import Foundation
import Resources
import Moya

public struct TwitterErrors: Codable {
    public let errors: [TwitterError]
    
    public static func handleTwitterError(_ errors: TwitterErrors) -> String {
        guard let code = errors.errors.first?.code else { return L10n.RequestError.unknownError }
        switch code {
        case 50:
            return L10n.FindUser.userNotFound
        case 34:
            return L10n.TwitterError.couldNotLoadPage
        default:
            return errors.errors.first?.message ?? L10n.RequestError.unknownError
        }
    }
}

public struct TwitterError: Codable {
    public let message: String
    public let code: Int
}

public enum TwitterMoyaError: Swift.Error {
    case twitterError(Response)
    case testError(TwitterErrors)
}

public extension TwitterMoyaError {
    var response: Moya.Response? {
        switch self {
        case .twitterError(let response):
            return response
        case .testError:
            return nil
        }
    }
}

extension TwitterMoyaError: LocalizedError {
    public var errorDescription: String? {
        switch self {
        case .twitterError(let response):
            do {
                return TwitterErrors.handleTwitterError(try response.map(TwitterErrors.self))
            } catch let error {
                return error.localizedDescription
            }
        case .testError(let errors):
            return TwitterErrors.handleTwitterError(errors)
        }
    }
}
