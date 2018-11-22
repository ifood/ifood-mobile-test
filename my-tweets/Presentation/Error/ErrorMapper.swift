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
        case .generic: return ""
        case .noInternetConnection: return ""
        case .badRequest(let message): return message ?? ""
        case .unexpected: return ""
        case .notFound: return ""
        }
    }
}

