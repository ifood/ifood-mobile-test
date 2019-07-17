//
//  Requester.swift
//  Movile-Test
//
//  Created by Thiago Felipe Alves on 14/07/19.
//  Copyright © 2019 Thalves. All rights reserved.
//

import Foundation
import UIKit
import SystemConfiguration

/// Closure that get the success requested object
/// - Parameter: object: Value (Generic)
typealias CompletionWithSuccess<Value> = ( (_ object: Value) -> Void )
typealias CompletionSuccess = ( () -> Void )

/// Closure that get the error
/// - Parameter: failure: Error
typealias CompletionWithFailure = ( (_ failure: NSError) -> Void )
typealias CompletionFailure = ( () -> Void )

class Requester: NSObject {

    enum TimeoutRequestType: TimeInterval {
        case normal = 30.0
    }

    var timeoutRequestType: TimeoutRequestType
    /// Variable that stores the environmet parâmeter.
    var env: String

    override init() {
        self.env = API.Environment.twitter.getValue()
        self.timeoutRequestType = .normal
        super.init()
    }

    /// Initializer with the selected environment
    /// - Parameter: environment: API.Environment
    convenience init(withEnvironment environment: API.Environment, timeoutType: TimeoutRequestType? = nil) {
        self.init()
        self.env = environment.getValue()
        timeoutRequestType = (timeoutType == nil) ? .normal : timeoutType!
    }

    /// Return the full URI to deal with the request
    /// - Parameter: endpoint: The given endpoint in String Format
    func urlComposer(using endpoint: Endpoint.EndpointType) -> (url: URL?, method: String) {

        switch endpoint.domain {
        case .Twitter:
            self.env = API.Environment.twitter.getValue()
        case .Google:
            break
        }

        let url = (url: URL(string: "\(env)\(endpoint.uri)"), method: endpoint.method)
        return url
    }

    func urlComposer(using endpoint: Endpoint.EndpointType, complement: String) -> (url: URL?, method: String) {
        let url = (url: URL(string: "\(env)\(endpoint.uri)\(complement)"), method: endpoint.method)
        return url
    }

    /// Create an URLRequest Object with the given URL
    ///
    /// - Parameters:
    ///   - urlEndpoint: URL - The full URL to be requested
    ///   - headers: HeaderHandler.Header - The request headers
    ///   - body: [String: String] - The request body
    /// - Returns: URLRequest
    func requestComposer(using urlEndpoint: (url: URL, method: String),
                         headers: [String: String],
                         body: [String: Any]? = nil) -> URLRequest {

        var request = URLRequest(url: urlEndpoint.url)
        request.allHTTPHeaderFields = headers
        request.httpMethod = urlEndpoint.method

        if let body = body {
            let isGet = urlEndpoint.method == "GET"
            let isUrlEncoded = headers["Content-Type"] == "application/x-www-form-urlencoded"

            if isGet || isUrlEncoded {
                let paramsUrlEncoded = getParamsUrlEncoded(body: body)

                if isGet {
                    let urlEndpoint = urlEndpoint.url.absoluteString + "?\(paramsUrlEncoded)"
                    request.url = URL(string: urlEndpoint)
                } else {
                    request.httpBody = paramsUrlEncoded.data(using: .utf8)
                }
            } else {
                let jsonData = try? JSONSerialization.data(withJSONObject: body)
                request.httpBody = jsonData
            }
        }

        return request
    }

    private func getParamsUrlEncoded(body: [String: Any]) -> String {
        var getParams = ""

        for key in body.keys {
            getParams += "\(key)=\(body[key]!)&"
        }
        let lastChar = getParams.index(getParams.endIndex, offsetBy: -1)
        let getParam = String(getParams[..<lastChar])

        return getParam
    }

    /// Session Configuration for all requests
    ///
    /// - Returns: URLSessionConfiguration
    func sessionConfigComposer() -> URLSessionConfiguration {

        let config = URLSessionConfiguration.default
        config.urlCache = nil
        config.urlCredentialStorage = nil
        config.httpCookieAcceptPolicy = .always
        config.requestCachePolicy = .reloadIgnoringLocalAndRemoteCacheData
        config.timeoutIntervalForRequest = timeoutRequestType.rawValue
        config.waitsForConnectivity = false

        return config
    }

    /// Generate the request using the given URLRequest
    ///
    /// - Parameters:
    ///   - request: URLRequest - The given request
    ///   - completion: (Data?, Error?)->Void The completion block that use the created task to do the job
    func dataTask(using request: URLRequest,
                  completion: @escaping (( _ data: Data?, _ error: NSError?) -> Void )) {
        let session = URLSession(configuration: sessionConfigComposer(), delegate: self, delegateQueue: nil)
        if Reachability.isConnectedToNetwork() {
            session.dataTask(with: request, completionHandler: { data, _, error in

                if error == nil && data == nil {
                    let err = NSError(domain: "Não conseguiu conectar.", code: -101, userInfo: nil)
                    completion(data, err)
                    return
                }

                if error == nil {
                    completion(data, nil)
                } else {
                    let generic = NSError(domain: "Tente novamente mais tarde", code: 0, userInfo: [:])
                    completion(data, generic)
                }

            }).resume()
            session.finishTasksAndInvalidate()
        } else {
            let error = NSError(domain: "Sem conexão com a internet.", code: -100, userInfo: nil)
            completion(nil, error)
        }
    }


    /// Basic parser to easily deal with object parse
    ///
    /// - Parameters:
    ///   - object: T - The Object type
    ///   - data: Data - The data that will be parsed to object
    /// - Returns: T?
    func JSONDecode<T: Codable>(to object: T.Type, from data: Data) -> T? {
        do {
            let object = try JSONDecoder().decode(T.self, from: data) as T
            return object
        } catch let error {
            if object != Error.self {
                print("\n❓JSONDecoder -> \(T.self): \(error)\n")
            }

            return nil
        }
    }

    func getParseError(data: Data) -> NSError {
        //        let message = String(data: data, encoding: String.Encoding.utf8)
        return NSError(domain: "Tente novamente mais tarde!", code: 1, userInfo: [:])
    }
}

extension Requester: URLSessionDelegate {
    func urlSession(_ session: URLSession,
                    didReceive challenge: URLAuthenticationChallenge,
                    completionHandler: @escaping (URLSession.AuthChallengeDisposition, URLCredential?) -> Void) {
        completionHandler(.performDefaultHandling, nil)
    }
}

public class Reachability {
    class func isConnectedToNetwork() -> Bool {

        var zeroAddress = sockaddr_in(sin_len: 0,
                                      sin_family: 0,
                                      sin_port: 0,
                                      sin_addr: in_addr(s_addr: 0),
                                      sin_zero: (0, 0, 0, 0, 0, 0, 0, 0))
        zeroAddress.sin_len = UInt8(MemoryLayout.size(ofValue: zeroAddress))
        zeroAddress.sin_family = sa_family_t(AF_INET)

        let defaultRouteReachability = withUnsafePointer(to: &zeroAddress) {
            $0.withMemoryRebound(to: sockaddr.self, capacity: 1) {zeroSockAddress in
                SCNetworkReachabilityCreateWithAddress(nil, zeroSockAddress)
            }
        }

        var flags = SCNetworkReachabilityFlags(rawValue: 0)
        if SCNetworkReachabilityGetFlags(defaultRouteReachability!, &flags) == false {
            return false
        }

        // Working for Cellular and WIFI
        let isReachable = (flags.rawValue & UInt32(kSCNetworkFlagsReachable)) != 0
        let needsConnection = (flags.rawValue & UInt32(kSCNetworkFlagsConnectionRequired)) != 0
        let ret = (isReachable && !needsConnection)

        return ret

    }
}
