//
//  TwitterError.swift
//  Domain
//
//  Created by Jean Vinge on 27/12/18.
//  Copyright © 2018 Jean Vinge. All rights reserved.
//

import Foundation
import Resources

public struct TwitterErrors: Codable {
    public let errors: [TwitterError]
    
    public static func handleTwitterError(_ errors: TwitterErrors) -> String {
        guard let code = errors.errors.first?.code else { return L10n.RequestError.unknownError }
        switch code {
        case 50:
            return L10n.FindUser.userNotFound
        default:
            return L10n.RequestError.unknownError
        }
    }
}

public struct TwitterError: Codable {
    public let message: String
    public let code: Int
}
