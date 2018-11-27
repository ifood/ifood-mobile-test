//
//  GetUserTimeline.swift
//  my-tweets
//
//  Created by Gabriel Catice on 27/11/18.
//  Copyright © 2018 Gabriel Catice. All rights reserved.
//

import Foundation
import RxSwift

public extension GetUserTimeline {
    public struct Request {
        public let screenName: String
    }
}

public struct GetUserTimeline {
    let executorScheduler: ImmediateSchedulerType
    let postExecutionScheduler: ImmediateSchedulerType
    
    let controller: UserController
}

extension GetUserTimeline: SingleUseCase {
    func execute(request: Request?) -> Single<[Tweet]> {
        guard let screenName = request?.screenName else { return Single.error(DomainError.missingRequest)}
        return self.controller.getTimeline(screenName: screenName)
    }
}
