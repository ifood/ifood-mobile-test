//
//  ErrorMapper.swift
//  my-tweets
//
//  Created by Gabriel Catice on 22/11/18.
//  Copyright © 2018 Gabriel Catice. All rights reserved.
//

extension DomainError {
    var message: String {
        switch self {
        case .generic: return R.string.localizable.unexpected_error()
        case .noInternetConnection: return R.string.localizable.check_your_internet_connection()
        case .badRequest(let message): return message ?? ""
        case .unexpected: return R.string.localizable.unexpected_error()
        case .notFound: return ""
        case .unauthorized: return ""
        case .missingRequest: return ""
        }
    }
}

