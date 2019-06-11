//
//  FindTwitterServiceMock.swift
//  IfoodMobileTestTests
//
//  Created by André Vieira on 10/06/19.
//  Copyright © 2019 André Vieira. All rights reserved.
//

import Foundation
import RxSwift

@testable import IfoodMobileTest

final class FindTwitterServiceMock: FindTwitterService {
    
    var errorWithoutInternet: Bool = false
    var errorTwitterNotFound: Bool = false
    var errorGeneric: Bool = false
    
    func getAccessToken() -> Observable<OAuthModel> {
        return Observable.just(OAuthModel(tokenType: "bearer", accessToken: "AAAAAAAAAAAAAAAAA"))
    }
    
    func getTweets(screenName: String) -> Observable<[TweetModel]> {
        
        if errorWithoutInternet {
            return Observable.error(DataError.withoutInternet)
        }
        
        if errorTwitterNotFound {
            return Observable.error(DataError.statusCode(404))
        }
        
        if errorGeneric {
            return Observable.error(DataError.generic(message: "generic error"))
        }
        
        return Observable.just([TweetModel(text: "Today's new update means that you can finally add Pizza",
                                          id: 1125490788736032770,
                                          createdAt: "Mon May 06 20:01:29 +0000 2019",
                                          user: TwitterUser(id: "2244994945",
                                                            name: "Twitter Dev",
                                                            screenName: "TwitterDev",
                                                            profileImageURL: URL(string: "https://pbs.twimg.com/profile_images/880136122604507136/xHrnqf1T_normal.jpg")))])
    }
}

