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
    func getTweets(screenName: String) -> Observable<[TweetModel]>
}

final class FindTwitterServiceImpl: FindTwitterService {
    typealias Target = TwitterTargetType
    private var provider: ProviderType<Target>
    
    init(provider: ProviderType<Target> = RequestProvider<TwitterTargetType>()) {
        self.provider = provider
    }
    
    func getAccessToken() -> Observable<OAuthModel> {
        let token = UserDefaults.getToken()
        guard token.isEmpty else {
            return Observable.just(OAuthModel(tokenType: nil, accessToken: token))
        }
        
        return provider
            .requestObject(Target.oauth)
            .do(onNext: { oauth in
                UserDefaults.save(oauth.accessToken ?? "")
            })
    }
    
    func getTweets(screenName: String) -> Observable<[TweetModel]> {
        return getAccessToken().flatMapLatest {[weak self] _ -> Observable<[TweetModel]> in
            return self?.provider.requestArray(Target.findTweets(screenName)) ?? Observable.just([])
        }
    }
}
