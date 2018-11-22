//
//  UseCaseTypes.swift
//  my-tweets
//
//  Created by Gabriel Catice on 22/11/18.
//  Copyright © 2018 Gabriel Catice. All rights reserved.
//

import RxSwift

protocol AutoInit {}

protocol UseCase {
    var executorScheduler: ImmediateSchedulerType { get }
    var postExecutionScheduler: ImmediateSchedulerType { get }
}

protocol ObservableUseCase: UseCase {
    associatedtype ReturnType
    associatedtype Req
    
    func execute(request: Req?) -> Observable<ReturnType>
}

protocol SingleUseCase: UseCase {
    associatedtype ReturnType
    associatedtype Req
    
    func execute(request: Req?) -> Single<ReturnType>
}

protocol CompletableUseCase: UseCase {
    associatedtype Req
    
    func execute(request: Req?) -> Completable
}

extension CompletableUseCase {
    func execute() -> Completable {
        return execute(request: nil).subscribeOn(executorScheduler).observeOn(postExecutionScheduler)
    }
}

extension SingleUseCase {
    func execute() -> Single<ReturnType> {
        return execute(request: nil).subscribeOn(executorScheduler).observeOn(postExecutionScheduler)
    }
}

extension ObservableUseCase {
    func execute() -> Observable<ReturnType> {
        return execute(request: nil).subscribeOn(executorScheduler).observeOn(postExecutionScheduler)
    }
}
