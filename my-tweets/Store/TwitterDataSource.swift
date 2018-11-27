//
//  TwitterDataSource.swift
//  my-tweets
//
//  Created by Gabriel Catice on 22/11/18.
//  Copyright © 2018 Gabriel Catice. All rights reserved.
//

import Foundation
import TwitterKit
import RxSwift
import Moya

public struct TwitterDataSource {
    let provider: TWTRTwitter
    let disposeBag = DisposeBag()
    
    public init(provider: TWTRTwitter) {
        self.provider = provider
    }
    
    func getUserTimeline(provider: TwitterProvider) -> Single<[TweetRM]> {
        return self.getApiClient().flatMap({ client in
            return self.request(target: provider, client: client)
                .mapDomainError()
                .mapDecodableEntity()
        })
    }
    
    func request(target: TwitterProvider, client: TWTRAPIClient) -> RxSwift.PrimitiveSequence<RxSwift.SingleTrait, Response> {
        var clientError: NSError?
        var networkResponse: Response?
        
        var request = client.urlRequest(withMethod: target.method.rawValue,
                                        urlString: target.endpoint,
                                        parameters: target.parameters,
                                        error: &clientError)
        request.timeoutInterval = 15
        
        client.sendTwitterRequest(request) { (response, data, connectionError) -> Void in
            networkResponse = Response(statusCode: 200, data: data!, request: nil, response: nil)
        }
        
        return Single.just(networkResponse!)
    }
    
    func getApiClient() -> Single<TWTRAPIClient> {
        var userId: String?
        if provider.sessionStore.hasLoggedInUsers(),
            let session = provider.sessionStore.session() {
            return Single.just(TWTRAPIClient(userID: session.userID))
        }
        
        provider.logIn { (session, error) in
            guard let userID = session?.userID else { return }
            userId = userID
            return
        }
        
        return Single.just(TWTRAPIClient(userID: userId))
    }
}
