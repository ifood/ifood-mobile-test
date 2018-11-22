//
//  ApplicationConfigurator.swift
//  my-tweets
//
//  Created by Gabriel Catice on 22/11/18.
//  Copyright © 2018 Gabriel Catice. All rights reserved.
//

import Moya
import Swinject
import SwinjectAutoregistration
import RxSwift

struct Scheduler {
    static let main = "MainScheduler"
    static let background = "BackgroundScheduler"
}

func buildApplicationContainer() -> Container {
    let container = Container()
    CustomConfigurator.setup(with: container)
    DataSourceConfigurator.setup(with: container)
    UseCaseConfigurator.setup(with: container)
    RepositoryConfigurator.setup(with: container)
    DeviceControllerConfigurator.setup(with: container)
    CoordinationConfigurator.setup(with: container)
    return container
}

enum CustomConfigurator {
    static func setup(with container: Container) {
        container.register(MoyaProvider<ServiceProvider>.self) { _ in return MoyaProvider<ServiceProvider>(endpointClosure: MoyaEndpoint.headerClosure, plugins: [NetworkLoggerPlugin(verbose: true)]) }
        
        container.register(ImmediateSchedulerType.self, name: Scheduler.main) { _ -> ImmediateSchedulerType in
            MainScheduler.instance
        }
        
        container.register(ImmediateSchedulerType.self, name: Scheduler.background) { _ -> ImmediateSchedulerType in
            MainScheduler.asyncInstance
        }
    }
}

