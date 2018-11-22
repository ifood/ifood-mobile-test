//
//  RxSwift+Extensions.swift
//  my-tweets
//
//  Created by Gabriel Catice on 22/11/18.
//  Copyright © 2018 Gabriel Catice. All rights reserved.
//

import RxSwift
import Foundation

extension PrimitiveSequenceType where Self.ElementType == Never, Self.TraitType == RxSwift.CompletableTrait {
    public static func fromAction(action: @escaping () -> Void) -> RxSwift.Completable {
        return Completable.empty().do(onCompleted: action)
    }
    
    func synchronize(semaphore: DispatchSemaphore) -> Completable {
        return self.do(onCompleted: { semaphore.signal() }, onSubscribe: { semaphore.wait() })
    }
}
