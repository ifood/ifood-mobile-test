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
        })
    }
    
    func request(target: TwitterProvider, client: TWTRAPIClient) -> Single<[TweetRM]> {
        var clientError: NSError?
        var parsedData = [TweetRM]()
        
        var request = client.urlRequest(withMethod: target.method.rawValue,
                                        urlString: target.endpoint,
                                        parameters: target.parameters,
                                        error: &clientError)
        request.timeoutInterval = 15
        
        return Single<[TweetRM]>.create(subscribe: { (observer) -> Disposable in
            client.sendTwitterRequest(request) { (response, data, connectionError) -> Void in
                do {
                    if connectionError != nil {
                        observer(.error(DomainError.generic))
                        return
                    }
                    parsedData = try JSONDecoder().decode([TweetRM].self, from: data!)
                    observer(.success(parsedData))
                } catch {
                    observer(.error(DomainError.generic))
                }
            }
            
            return Disposables.create()
        })
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
