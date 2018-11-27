//
//  DomainError.swift
//  my-tweets
//
//  Created by Gabriel Catice on 22/11/18.
//  Copyright © 2018 Gabriel Catice. All rights reserved.
//

import Foundation

import Foundation

enum DomainError: Error {
    case generic
    case noInternetConnection
    case badRequest(message: String?)
    case unexpected
    case notFound
    case unauthorized
    case missingRequest
}
