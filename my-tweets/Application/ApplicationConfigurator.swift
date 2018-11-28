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
import TwitterKit

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
    CoordinationConfigurator.setup(with: container)
    return container
}

enum CustomConfigurator {
    static func setup(with container: Container) {
        TWTRTwitter.sharedInstance().start(withConsumerKey: R.string.localizable.costumer_key(), consumerSecret: R.string.localizable.costumerSecret())
        let twitter = TWTRTwitter.sharedInstance()

        container.register(MoyaProvider<GoogleProvider>.self) { _ in return MoyaProvider<GoogleProvider>(endpointClosure: MoyaEndpoint.headerClosure, plugins: [NetworkLoggerPlugin(verbose: true)]) }
        
        container.register(ImmediateSchedulerType.self, name: Scheduler.main) { _ -> ImmediateSchedulerType in
            MainScheduler.instance
        }
        
        container.register(ImmediateSchedulerType.self, name: Scheduler.background) { _ -> ImmediateSchedulerType in
            MainScheduler.asyncInstance
        }
    }
}

