//
//  ObversableType+Moya.swift
//  my-tweets
//
//  Created by Gabriel Catice on 22/11/18.
//  Copyright © 2018 Gabriel Catice. All rights reserved.
//

import Foundation
import RxSwift
import Moya

extension RxSwift.PrimitiveSequence where Trait == RxSwift.SingleTrait, Element == Response {
    func mapDecodableEntity<T: Decodable>() -> Single<T> {
        return map { response -> T in
            guard let entity = try? response.map(T.self) else {
                throw DomainError.generic
            }
            
            return entity
        }
    }
    
    func mapDomainError() -> PrimitiveSequence<SingleTrait, Response> {
        return catchError({ err in
            if let error = err as? MoyaError {
                switch error {
                case .underlying(let nsError as NSError, _):
                    switch nsError.code {
                    case NSURLErrorNotConnectedToInternet:
                        return Single.error(DomainError.noInternetConnection)
                    default:
                        return Single.error(DomainError.unexpected)
                    }
                default:
                    return Single.error(DomainError.unexpected)
                }
            }
            
            // not a MoyaError
            return Single.error(DomainError.unexpected)
        }).flatMap { moyaResponse in
            do {
                // Filter out status codes 200...399, throw MoyaError otherwise
                let filteredResponse = try moyaResponse.filterSuccessfulStatusAndRedirectCodes()
                return Single.just(filteredResponse)
            } catch {
                switch moyaResponse.statusCode {
                case 400:   // bad request
                    return Single.error(DomainError.badRequest(message: nil))
                case 404:
                    return Single.error(DomainError.notFound)
                case 401, 403:
                    return Single.error(DomainError.unauthorized)
                case 500: // unexpected
                    return Single.error(DomainError.unexpected)
                default:
                    return Single.error(error)
                }
            }
        }
        
    }
}
