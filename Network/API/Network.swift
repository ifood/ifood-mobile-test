//
//  Network.swift
// TwitterSentiment
//
//  Created by Jean Vinge on 20/06/2018.
//  Copyright © 2018 Atlas Politico. All rights reserved.
//

import Alamofire
import Moya
import Utility

let timeoutInterval: TimeInterval = 15

final class SessionManager: Alamofire.SessionManager {

    // MARK: Init

    init() {
        let configuration = URLSessionConfiguration.default
        configuration.httpAdditionalHeaders = Alamofire.SessionManager.defaultHTTPHeaders

        configuration.timeoutIntervalForRequest = timeoutInterval
        configuration.timeoutIntervalForResource = timeoutInterval
        configuration.requestCachePolicy = .useProtocolCachePolicy
        super.init(configuration: configuration)
    }
}

import Moya

extension TargetType {

    var sampleData: Data {
        return Data.emptyJSON
    }

    var headers: [String: String]? {
        return nil
    }
}

struct Network {

    // MARK: Var

    let provider: MoyaProvider<MultiTarget>
    typealias NetworkStubCompletion = (_ multiTarget: MultiTarget) -> Endpoint

    // MARK: Init

    init(provider: MoyaProvider<MultiTarget>? = nil) {

        if let provider = provider {
            self.provider = provider
        } else {
            switch Environment.env {
            case .test, .uiTest:
                self.provider = Network.stubProvider()
            default:
                self.provider = MoyaProvider<MultiTarget>(manager: SessionManager(), plugins: [NetworkLoggerPlugin(verbose: true, responseDataFormatter: Data().JSONPretty)])
            }
        }
    }

    static func endPointClosure(statusCode: Int, data: Data? = nil) -> MoyaProvider<MultiTarget>.EndpointClosure {
        return { (multiTarget: MultiTarget) -> Endpoint in
            let sampleClosure = { () -> EndpointSampleResponse in
                return EndpointSampleResponse.networkResponse(statusCode, data == nil ? multiTarget.target.sampleData : data!)
            }
            return Endpoint(multiTarget: multiTarget, sampleClosure: sampleClosure)
        }
    }

    static func stubProvider(closure: @escaping NetworkStubCompletion) -> MoyaProvider<MultiTarget> {
        return MoyaProvider<MultiTarget>(endpointClosure: closure, stubClosure: MoyaProvider.immediatelyStub)
    }

    static func stubProvider() -> MoyaProvider<MultiTarget> {
        return Network.stubProvider(closure: Network.endPointClosure(statusCode: 200))
    }
}
