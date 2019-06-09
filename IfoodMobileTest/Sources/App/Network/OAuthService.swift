//
//  OAuthService.swift
//  IfoodMobileTest
//
//  Created by André Vieira on 09/06/19.
//  Copyright © 2019 André Vieira. All rights reserved.
//

import Foundation
import RxSwift

protocol OAuthService {
    func getAccessToken() -> Observable<OAuthModel>
}

final class OAuthServiceImpl: OAuthService {
    typealias Target = OAuthTargetType
    private var provider: ProviderType<Target>
    
    init(provider: ProviderType<Target> = RequestProvider<OAuthTargetType>()) {
        self.provider = provider
    }
    
    func getAccessToken() -> Observable<OAuthModel> {
        return provider.requestObject(Target.oauth)
    }
}
