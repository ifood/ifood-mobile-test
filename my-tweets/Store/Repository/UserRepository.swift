//
//  UserRepository.swift
//  my-tweets
//
//  Created by Gabriel Catice on 27/11/18.
//  Copyright © 2018 Gabriel Catice. All rights reserved.
//

import Foundation
import RxSwift

struct UserRepository {
    let twitterDataSource: TwitterDataSource
}

extension UserRepository: UserController {
    func getTimeline(screenName: String) -> Single<[Tweet]> {
        return twitterDataSource.getUserTimeline(provider: .timeline(screenName: screenName)).map {$0.toDomainModel()}
    }
}
