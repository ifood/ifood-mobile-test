//
//  HttpService.swift
//  Twimotion
//
//  Created by Antony on 26/05/18.
//  Copyright © 2018 Twimotion. All rights reserved.
//

import Foundation
import RxSwift

protocol TargetType {
    var baseURL: URL { get }
    var path: String { get }
    var method: HttpMethod { get }
    var body: Data? { get }
    var headers: [String: String]? { get }
}

extension TargetType {
    func urlRequest() -> URLRequest {

        // generate url
        guard let url = URLComponents(string: "\(baseURL.absoluteString)\(path)")?.url else {
            fatalError("invalid URL")
        }

        var request = URLRequest(url: url)

        /// http method
        request.httpMethod = self.method.rawValue

        /// body
        request.httpBody = self.body

        /// headers
        self.headers?.forEach { it in
            request.addValue(it.value, forHTTPHeaderField: it.key)
        }

        return request
    }
}

enum Result {
    case failure(Error)
    case success(Data)
}

enum HttpMethod: String {
    case get = "GET"
    case post = "POST"
    case put = "PUT"
    case delete = "DELETE"
}

enum HttpError: Swift.Error {
    case jsonMapping(Error?)
    case invalidJson
}

protocol HttpServiceType: class {
    associatedtype Target: TargetType
    func request(_ endpoint: Target, responseData: @escaping (Result) -> Void) -> URLSessionDataTask
}

class HttpService<Target: TargetType>: HttpServiceType {

    private var requestClosure: (Target) -> URLRequest
    private var session: URLSession

    // MARK: - Initializer

    init(
        urlSession: URLSession? = nil,
        requestClosure: @escaping ((Target) -> URLRequest) = { $0.urlRequest() }
    ) {
        self.session = URLSession(
            configuration: URLSessionConfiguration.default,
            delegate: HttpServiceSessionDelegate(),
            delegateQueue: nil
        )
        self.requestClosure = requestClosure
    }

    func request(_ endpoint: Target, responseData: @escaping (Result) -> Void) -> URLSessionDataTask {
        //1 - pass through interceptors
        let request = requestClosure(endpoint)

        //2 - execute task
        let task = self.session.dataTask(with: request) { data, _, error in
            guard let data = data else {
                responseData(Result.failure(error!))
                return
            }
            responseData(Result.success(data))
        }
        task.resume()

        //3 - return task
        return task
    }

}

class HttpServiceSessionDelegate: NSObject, URLSessionDelegate {

    func urlSession(_ session: URLSession, didReceive challenge: URLAuthenticationChallenge, completionHandler: @escaping (URLSession.AuthChallengeDisposition, URLCredential?) -> Void) {
        if challenge.previousFailureCount > 0 {
            completionHandler(URLSession.AuthChallengeDisposition.cancelAuthenticationChallenge, nil)
        } else if let serverTrust = challenge.protectionSpace.serverTrust {
            completionHandler(URLSession.AuthChallengeDisposition.useCredential, URLCredential(trust: serverTrust))
        } else {
            print("unknown state. error: ")
        }
    }
}
