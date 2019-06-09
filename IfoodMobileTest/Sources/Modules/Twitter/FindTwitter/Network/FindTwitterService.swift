//
//  FindTwitterService.swift
//  IfoodMobileTest
//
//  Created by André Vieira on 09/06/19.
//  Copyright © 2019 André Vieira. All rights reserved.
//

import Foundation
import RxSwift

protocol FindTwitterService {
    func getAccessToken() -> Observable<OAuthModel>
    func getTweets(userName: String) -> Observable<Void>
}

final class FindTwitterServiceImpl: OAuthService {
    typealias Target = TwitterTargetType
    private var provider: ProviderType<Target>
    
    init(provider: ProviderType<Target> = RequestProvider<TwitterTargetType>()) {
        self.provider = provider
    }
    
    func getAccessToken() -> Observable<OAuthModel> {
        return provider
            .requestObject(Target.oauth)
            .do(onNext: { oauth in
                UserDefaults.save(oauth.accessToken ?? "")
            })
    }
}
