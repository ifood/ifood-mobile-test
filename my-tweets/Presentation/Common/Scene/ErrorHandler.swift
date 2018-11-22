//
//  ErrorHandler.swift
//  my-tweets
//
//  Created by Gabriel Catice on 22/11/18.
//  Copyright © 2018 Gabriel Catice. All rights reserved.
//

import Foundation

protocol ErrorHandler {
    func handleGenericError(error: Error, isBlocking: Bool)
}

extension ErrorHandler where Self: ScenePresenter {
    func handleGenericError(error: Error, isBlocking: Bool) {
        var message: String?
        
        if let domainError = error as? DomainError {
            switch domainError {
            case .noInternetConnection:
                if isBlocking {
                    sceneView?.displayBlockingNoInternetError()
                } else {
                    sceneView?.displayNonBlockingNoInternetError()
                }
                return
            case .badRequest(let badRequestMessage):
                message = badRequestMessage
            default:
                message = domainError.message
            }
        }
        
        // Fallback to generic error
        if isBlocking {
            sceneView?.displayBlockingGenericError(message: message)
        } else {
            sceneView?.displayNonBlockingGenericError(message: message)
        }
    }
}

