//
//  FindUserViewModel.swift
//  TwitterSentiment
//
//  Created by Jean Vinge on 25/12/18.
//  Copyright © 2018 Jean Vinge. All rights reserved.
//

import Foundation
import RxSwift
import RxCocoa
import Utility
import Domain

struct FindUserViewModel: ViewModelProtocol {
    
    // MARK: Var
    
    var coordinator: CoordinatorProvider
    var useCase: TwitterUseCase
    
    // MARK: Init
    
    init(useCase: TwitterUseCase, coordinator: CoordinatorProvider) {
        self.coordinator = coordinator
        self.useCase = useCase
    }
    
    func transform(input: Input) -> Output {
        let activity = ActivityIndicator()
        let twitterUser = input.searchUserName.flatMapLatest { (text: String) -> Driver<TwitterUser> in
            return self.useCase.user(text).trackActivity(activity).do(onNext: { user in
                _ = self.coordinator.transition(with: FindUserRouter.toTweetList(user: user, useCase: self.useCase))
            }, onError: { error in
                _ = self.coordinator.transition(with: FindUserRouter.error(error: error))
            }).asDriverOnErrorJustComplete()
            }
        return Output(toUserTweetList: twitterUser, fetching: activity.asDriver())
    }
}

extension FindUserViewModel {
    struct Input {
        let searchUserName: Driver<String>
    }

    struct Output {
        let toUserTweetList: Driver<TwitterUser>
        let fetching: Driver<Bool>
    }
}
