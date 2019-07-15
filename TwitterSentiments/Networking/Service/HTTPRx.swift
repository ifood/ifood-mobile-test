//
//  HTTPRx.swift
//  TwitterSentiments
//
//  Created by Marina Azevedo on 14/07/19.
//  Copyright © 2019 Marina Azevedo. All rights reserved.
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
    public func map<D: Decodable>(_ type: D.Type, using decoder: JSONDecoder = JSONDecoder.sharedDecoder) -> Single<D> {
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

