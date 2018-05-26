//
//  HttpService.swift
//  Twimotion
//
//  Created by Antony on 26/05/18.
//  Copyright © 2018 Twimotion. All rights reserved.
//

import Foundation

protocol TargetType {
    var baseURL: URL { get }
    var path: String { get }
    var method: HttpMethod { get }
    var jsonParameters: [String: Any] { get }
    var headers: [String: String]? { get }
}

extension TargetType {
    func urlRequest() -> URLRequest {

        // generate url
        let urlPath = [self.baseURL.absoluteString, self.path].joined()
        let url = URL(string: urlPath)!

        var request = URLRequest(url: url)

        /// http method
        request.httpMethod = self.method.rawValue

        /// body
        switch self.method {
        case .get: break
        default:
            request.httpBody = try? JSONSerialization.data(withJSONObject: self.jsonParameters, options: [])
        }

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
        configuration: URLSessionConfiguration = URLSessionConfiguration.default,
        requestClosure: @escaping ((Target) -> URLRequest) = { $0.urlRequest() }
        ) {
        self.session = URLSession(configuration: configuration)
        self.requestClosure = requestClosure
    }

    func request(_ endpoint: Target, responseData: @escaping (Result) -> Void) -> URLSessionDataTask {
        //1 - pass through interceptors
        let request = requestClosure(endpoint)

        //2 - execute task
        let task = URLSession.shared.dataTask(with: request) { data, _, error in
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
