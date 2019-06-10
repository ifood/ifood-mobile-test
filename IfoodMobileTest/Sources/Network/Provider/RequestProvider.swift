//
//  RequestProvider.swift
//  IfoodMobileTest
//
//  Created by André Vieira on 09/06/19.
//  Copyright © 2019 André Vieira. All rights reserved.
//

import Foundation
import RxSwift

final class RequestProvider<Target: TargetType>: ProviderType<Target> {
    
    private let endpoint: ((_ target: Target) -> URLRequest)
    
    public override init() {
        endpoint = { (target: Target) -> URLRequest in
            let request = URLRequest(url: target.baseURL.appendingPathComponent(target.path))
            return request
        }
    }
    
    override func requestArray<Model: Codable>(_ target: Target) -> Observable<[Model]> {
        return self.doRequest(target).flatMap({ response -> Observable<[Model]> in
            return Observable.just(try response.data.mapToArray())
        })
    }
    
    override func requestObject<Model: Codable>(_ target: Target) -> Observable<Model> {
        return self.doRequest(target).flatMap({ response -> Observable<Model> in
            return Observable.just(try response.data.mapToObject())
        })
    }
    
    override func requestJSON(_ target: Target) -> Observable<(response: URLResponse, data: Data)> {
        return self.doRequest(target)
    }
    
    private func doRequest(_ target: Target) -> Observable<(response: URLResponse, data: Data)> {
        let session = URLSession.shared
        return Observable.create {[weak self] observer in
            
            guard CheckInternet.connection() else {
                observer.onError(DataError.withoutInternet)
                observer.onCompleted()
                return Disposables.create()
            }
            
            guard var request = self?.endpoint(target) else {
                observer.onError(DataError.generic(message: "couldn't access self for target: \(target.self)"))
                observer.onCompleted()
                return Disposables.create()
            }
            request.httpMethod = target.method.rawValue
            request.allHTTPHeaderFields = target.headers
            do {
                request = try target.parameterEncoding.encode(request: request, parameters: target.parameters)
            } catch let error {
                observer.onError(error)
                observer.onCompleted()
            }
            
            let task = session.dataTask(with: request, completionHandler: { data, response, error in
                
                if let httpResponse = response as? HTTPURLResponse {
                    if let error = error {
                        observer.onError(error)
                        observer.onCompleted()
                    }
                    
                    if httpResponse.statusCode >= 300 && httpResponse.statusCode <= 500 {
                        observer.onError(DataError.statusCode(httpResponse.statusCode))
                        observer.onCompleted()
                    }
                    
                    if let response = response, let data = data {
                        observer.onNext((response: response, data: data))
                        observer.onCompleted()
                    }
                } else {
                    observer.onError(DataError.generic(message: "response invalid"))
                    observer.onCompleted()
                }
            })
            
            task.resume()
            return Disposables.create {
                task.cancel()
            }
            
        }
    }
}
