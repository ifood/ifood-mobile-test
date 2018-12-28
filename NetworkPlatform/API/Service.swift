//
//  Service.swift
//  matchAcesso
//
//  Created by Jean Vinge on 20/08/2018.
//  Copyright © 2018 Atlas Politico. All rights reserved.
//

import Moya
import RxSwift
import RxCocoa
import Alamofire
import Domain

extension Moya.Endpoint {
    public convenience init(multiTarget: MultiTarget, sampleClosure: @escaping Moya.Endpoint.SampleResponseClosure) {
        self.init(url: multiTarget.target.path, sampleResponseClosure: sampleClosure, method: multiTarget.target.method, task: multiTarget.target.task, httpHeaderFields: multiTarget.target.headers)
    }
}

protocol ServiceType {
    var network: NetworkProvider { get set }
}

final class Service: NSObject, ServiceType {

    var network: NetworkProvider

    init(_ provider: MoyaProvider<MultiTarget>? = nil) {
        self.network = NetworkProvider(provider: provider)
    }

    func request(endpoint: TargetType) -> Single<Response> {
        return self.network.provider.rx.request(MultiTarget(endpoint)).handleRequest(with: self)
    }
}

extension Response {
    func handleStatusCode(with service: Service) throws -> Response {
        switch statusCode {
        case 200...299:
            return self
        case 300...399, 400...499, 500...599:
            throw TwitterMoyaError.twitterError(self)
        default:
            throw MoyaError.statusCode(self)
        }
    }
}

extension PrimitiveSequence where TraitType == SingleTrait, ElementType == Response {
    func handleRequest(with service: Service) -> Single<ElementType> {
        return flatMap { response -> Single<ElementType> in
            return .just(try response.handleStatusCode(with: service))
        }
    }
}
