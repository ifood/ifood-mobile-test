//
//  Observable+Extensions.swift
//  Utility
//
//  Created by Jean Vinge on 27/12/18.
//  Copyright © 2018 Jean Vinge. All rights reserved.
//

import RxSwift
import RxCocoa

extension SharedSequenceConvertibleType {
    public func mapToVoid() -> SharedSequence<SharingStrategy, Void> {
        return map { _ in }
    }
}

extension ObservableType {
    public func catchErrorJustComplete() -> Observable<E> {
        return catchError { _ in
            return .empty()
        }
    }
    public func asDriverOnErrorJustComplete() -> Driver<E> {
        return asDriver { _ in
            return .empty()
        }
    }
    public func mapToVoid() -> Observable<Void> {
        return map { _ in }
    }
}
