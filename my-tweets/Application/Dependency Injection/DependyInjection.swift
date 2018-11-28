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
        HomeConfigurator.setup(with: container)
    }
}

enum HomePresenterConfigurator {
    static func setup(with container: Container) {
        container.register(HomePresenterProtocol.self) { resolver in
            return HomePresenter(view: resolver.resolve(HomeViewProtocol?.self)!, getTimeline: resolver.resolve(GetUserTimeline.self)!)
        }
    }
}

// DataSource Dependency Injection
enum DataSourceConfigurator {
    static func setup(with container: Container) {
        container.autoregister(TwitterDataSource.self, initializer: TwitterDataSource.init)
        container.autoregister(SentimentDataSource.self, initializer: SentimentDataSource.init)
    }
}

// Repository Dependency Injection
public enum RepositoryConfigurator {
    public static func setup(with container: Container) {
        container.autoregister(UserController.self, initializer: UserRepository.init)
        container.autoregister(SentimentController.self, initializer: SentimentRepository.init)
    }
}

// Use Case Dependency Injection
public enum UseCaseConfigurator {
    public static func setup(with container: Container) {
        container.register(GetUserTimeline.self, factory: {
            GetUserTimeline(executorScheduler: $0.resolve(ImmediateSchedulerType.self, name: Scheduler.background)!, postExecutionScheduler: $0.resolve(ImmediateSchedulerType.self, name: Scheduler.main)!, controller: $0.resolve(UserController.self)!,
                            sentimentController: $0.resolve(SentimentController.self)!)
            
        })
    }
}
