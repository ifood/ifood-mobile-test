//
//  BaseCoordinator.swift
//  twitter-mood
//
//  Created by Dennis Merli on 7/6/19.
//  Copyright © 2019 Dennis Merli. All rights reserved.
//

import Foundation
import UIKit
import RxCocoa
import RxSwift

protocol CoordinatorType: class {
    func start()
    func start(presentationType: PresentationType)
    func finalize()
}

protocol DependencyCoordinatorType: CoordinatorType {
    var childCoordinators: [CoordinatorType] { get set }
    func coordinateTo(coordinator: CoordinatorType?)
    func addDependency(coordinator: CoordinatorType?)
    func removeDependency(coordinator: CoordinatorType?)
}

/// CoordinatorType and DependencyCoordinatorType
typealias DependencyAndCoordinatable = CoordinatorType & DependencyCoordinatorType

class BaseCoordinator: DependencyAndCoordinatable {
    
    init(window: UIWindow?, parentCoordinator: BaseCoordinator?) {
        self.parentCoordinator = parentCoordinator
    }
    
    init(rootViewControler: UIViewController?, parentCoordinator: BaseCoordinator?) {
        self.parentCoordinator = parentCoordinator
    }
    
    var disposeBag: DisposeBag = DisposeBag()
    
    var childCoordinators: [CoordinatorType] = []
    
    var parentCoordinator: BaseCoordinator?
    
    func start() {
        
    }
    
    func start(presentationType: PresentationType) {
        
    }
    
    func finalize() {
        
    }
}

extension BaseCoordinator {
    
    func coordinateTo(coordinator: CoordinatorType?) {
        guard let coordinator = coordinator else { return }
        if childCoordinators.contains(where: { $0 === coordinator }) {
            return
        }
        coordinator.start()
        addDependency(coordinator: coordinator)
    }
    
    func addDependency(coordinator: CoordinatorType?) {
        guard let coordinator = coordinator else { return }
        if childCoordinators.contains(where: { $0 === coordinator }) {
            return
        }
        childCoordinators.append(coordinator)
    }
    
    func removeDependency(coordinator: CoordinatorType?) {
        guard let coordinator = coordinator else { return }
        guard let index = childCoordinators.firstIndex(where: { $0 === coordinator }) else {
            return
        }
        childCoordinators.remove(at: index)
    }
}
