//
//  HttpService+Rx.swift
//  Twimotion
//
//  Created by Antony on 26/05/18.
//  Copyright © 2018 Twimotion. All rights reserved.
//

import Foundation
import RxSwift

extension HttpService: ReactiveCompatible { }

extension Reactive where Base: HttpServiceType {

    func request(_ endpoint: Base.Target) -> Single<Data> {
        return Single<Data>.create(subscribe: { [weak base] single in
            let task = base?.request(endpoint, responseData: { result in
                switch result {
                case Result.success(let data):
                    single(SingleEvent.success(data))
                case Result.failure(let error):
                    single(SingleEvent.error(error))
                }
            })

            return Disposables.create { task?.cancel() }
        })
    }
}

extension PrimitiveSequence where TraitType == SingleTrait, ElementType == Data {
    /// Maps received data at key path into a Decodable object. If the conversion fails, the signal errors.
    public func map<D: Decodable>(_ type: D.Type, using decoder: JSONDecoder = JSONDecoder()) -> Single<D> {
        return flatMap { data in
            do {
                let res = try decoder.decode(type, from: data)
                return Single<D>.just(res)
            } catch {
                return Single<D>.error(HttpError.jsonMapping(error))
            }

        }
    }
}

