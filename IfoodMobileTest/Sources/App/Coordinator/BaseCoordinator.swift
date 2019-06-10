//
//  BaseCoordinator.swift
//  IfoodMobileTest
//
//  Created by André Vieira on 09/06/19.
//  Copyright © 2019 André Vieira. All rights reserved.
//

import Foundation
import RxSwift

class BaseCoordinator<ResultType> {
    
    typealias CoordinationResult = ResultType
    let disposeBag = DisposeBag()
    private var identifier: String { return String(describing: self) }
    private var childCoordinators = [String: Any]()
    
    private func store<T>(coordinator: BaseCoordinator<T>) {
        childCoordinators[coordinator.identifier] = coordinator
    }
    
    private func free<T>(coordinator: BaseCoordinator<T>) {
        childCoordinators[coordinator.identifier] = nil
    }
    
    func coordinate<T>(to coordinator: BaseCoordinator<T>) -> Observable<T> {
        store(coordinator: coordinator)
        return coordinator.start()
            .do(onNext: { [weak self] _ in self?.free(coordinator: coordinator) })
    }
    
    func coordinate<T>(to coordinator: BaseCoordinator<T>, parameters: [String: Any]) -> Observable<T> {
        store(coordinator: coordinator)
        return coordinator.start(parameters: parameters)
            .do(onNext: { [weak self] _ in self?.free(coordinator: coordinator) })
    }
    
    func start() -> Observable<ResultType> {
        fatalError("Start method should be implemented.")
    }
    
    func start(parameters: [String: Any]) -> Observable<ResultType> {
        fatalError("Start method should be implemented.")
    }
}
