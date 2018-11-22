//
//  DependyInjection.swift
//  my-tweets
//
//  Created by Gabriel Catice on 22/11/18.
//  Copyright © 2018 Gabriel Catice. All rights reserved.
//

import Swinject
import SwinjectAutoregistration
import Moya
import RxSwift

// Presentation Dependency Injection
enum CoordinationConfigurator {
    static func setup(with container: Container) {
        
    }
}

//enum GenericPresenterConfigurator {
//    static func setup(with container: Container) {
//        container.register(AddressSearchPresenterProtocol.self) { resolver in
//            return AddressSearchPresenter(view: resolver.resolve(AddressSearchViewProtocol?.self)!,
//                                          searchCep: resolver.resolve(SearchCep.self)!,
//                                          getFederationUnits: resolver.resolve(GetFederationUnits.self)!,
//                                          validateField: resolver.resolve(ValidateField.self)!)
//        }
//    }
//}

// DataSource Dependency Injection
enum DataSourceConfigurator {
    static func setup(with container: Container) {
        
        // DataSource
        //        container.autoregister(AddressRemoteDataSource.self, initializer: AddressRemoteDataSource.init)
    }
}

// Repository Dependency Injection
public enum RepositoryConfigurator {
    public static func setup(with container: Container) {
        //        container.autoregister(AddressController.self, initializer: AddressRepository.init)
    }
}

// Controller Dependency Injection
public enum DeviceControllerConfigurator {
    public static func setup(with container: Container) {
        //        container.autoregister(BiometricAuthController.self, initializer: BiometricAuthDeviceController.init)
    }
}

// Use Case Dependency Injection
public enum UseCaseConfigurator {
    public static func setup(with container: Container) {
        //        container.register(CheckUserIsLoggedIn.self, factory: {
        //            CheckUserIsLoggedIn(controller: $0.resolve(AuthController.self)!,
        //                                executorScheduler: $0.resolve(ImmediateSchedulerType.self, name: Scheduler.background)!,
        //                                postExecutionScheduler: $0.resolve(ImmediateSchedulerType.self, name: Scheduler.main)!)
        //        })
        
    }
}
