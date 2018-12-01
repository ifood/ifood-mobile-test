//
//  AlamofireWrapper.swift
//  ifood-mobile-test
//
//  Created by Marcio Garcia on 30/11/18.
//  Copyright © 2018 oxltech.com. All rights reserved.
//

import Foundation
import Alamofire

class AlamofireServerTrustPolicyManager: ServerTrustPolicyManager {
    
    override public func serverTrustPolicy(forHost host: String) -> ServerTrustPolicy? {
        return ServerTrustPolicy.disableEvaluation
    }
}

class AlamofireSessionDelegate: SessionDelegate {
}

class AlamofireWrapper: SessionManager, RestService {
    
    private var environment: EnvironmentType = .release
    private var baseUrl: String = ""
    private var apiKey: String = ""
    private var appVersion: String = ""
    private var certificates: [SecCertificate] = []
    
    // MARK: - Private Initialization Methods
    
    init(environment: EnvironmentType, baseUrl: String, apiKey: String, appVersion: String) {
        
        self.environment = environment
        self.baseUrl = baseUrl
        self.apiKey = apiKey
        self.appVersion = appVersion
        
        let configuration = URLSessionConfiguration.default
        let serverTrustManager = AlamofireServerTrustPolicyManager(policies: [:])
        
        super.init(configuration: configuration,
                   delegate: AlamofireSessionDelegate.init(),
                   serverTrustPolicyManager: serverTrustManager)
        
    }
    
    // MARK: - Private Methods
    
    private func printResponse(_ response: DefaultDataResponse) {
        
        guard self.environment != .release else {
            return
        }
        
        if let request = response.request {
            
            if let requestHTTPHeaderFields = request.allHTTPHeaderFields {
                print("Request Headers: \(requestHTTPHeaderFields)")
            }
            
            if let requestBodyData = request.httpBody,
                let requestBody = String(data:requestBodyData, encoding:String.Encoding.utf8) {
                print("Request Body: \(requestBody)")
            }
            
            if let requestBodyStream = request.httpBodyStream {
                print("Request Body Stream: \(requestBodyStream)")
            }
        }
        
        if let response = response.response {
            print("Response: \(response)")
        }
        
        if let error = response.error {
            if let data = response.data {
                if let responseBody = String(data: data, encoding: String.Encoding.utf8) {
                    print("Response Body: \(responseBody)")
                }
            }
            print("Error: \(error)")
        }
        else {
            if let data = response.data {
                do {
                    let jsonObject = try JSONSerialization.jsonObject(with: data, options: .allowFragments)
                    print("ResponseBody: \(jsonObject)")
                }
                catch {
                    if let responseString = String(data: data, encoding: .utf8) {
                        print("ResponseString: \(responseString)")
                    }
                }
            }
        }
    }
    
    // MARK: - Internal Methods
    
    private func serviceUrl(_ url: String) -> String {
        let formedUrl = self.baseUrl + url
        if let url = formedUrl.addingPercentEncoding(withAllowedCharacters: NSCharacterSet.urlQueryAllowed) {
            return url
        }
        return ""
    }
    
    func defaultHeader() -> HTTPHeaders? {
        
        let defaultHeader: HTTPHeaders = [
            //"Authorization"  : "Basic \(encondedKey)",
            "Content-Type" : "application/json"
        ]
        
        return defaultHeader
    }
    
    func get(_ url: String,
             parameters: Parameters?,
             encoding: RequestParameterEncoding,
             headers: HTTPHeaders?,
             success: @escaping (Data?) -> (),
             failure: @escaping (NSError) -> ()) {
        
        var paramEncoding: ParameterEncoding!
        switch encoding {
        case .json:
            paramEncoding = JSONEncoding.default
        case .queryString:
            paramEncoding = URLEncoding.queryString
        }
        
        request(url,
                method: .get,
                parameters: parameters,
                encoding: paramEncoding,
                headers: headers,
                success: success,
                failure: failure)
    }
    
    func post(_ url: String,
              parameters: Parameters?,
              encoding: RequestParameterEncoding,
              headers: HTTPHeaders?,
              success: @escaping (Data?) -> (),
              failure: @escaping (NSError) -> ()) {
        
        var paramEncoding: ParameterEncoding!
        switch encoding {
        case .json:
            paramEncoding = JSONEncoding.default
        case .queryString:
            paramEncoding = URLEncoding.queryString
        }
        
        request(url,
                method: .post,
                parameters: parameters,
                encoding: paramEncoding,
                headers: headers,
                success: success,
                failure: failure)
    }
    
    func put(_ url: String,
             parameters: Parameters?,
             encoding: RequestParameterEncoding,
             headers: HTTPHeaders?,
             success: @escaping (Data?) -> (),
             failure: @escaping (NSError) -> ()) {
        
        var paramEncoding: ParameterEncoding!
        switch encoding {
        case .json:
            paramEncoding = JSONEncoding.default
        case .queryString:
            paramEncoding = URLEncoding.queryString
        }
        
        request(url,
                method: .put,
                parameters: parameters,
                encoding: paramEncoding,
                headers: headers,
                success: success,
                failure: failure)
    }
    
    func delete(_ url: String,
                parameters: Parameters?,
                encoding: RequestParameterEncoding,
                headers: HTTPHeaders?,
                success: @escaping (Data?) -> (),
                failure: @escaping (NSError) -> ()) {
        
        var paramEncoding: ParameterEncoding!
        switch encoding {
        case .json:
            paramEncoding = JSONEncoding.default
        case .queryString:
            paramEncoding = URLEncoding.queryString
        }
        
        request(url,
                method: .delete,
                parameters: parameters,
                encoding: paramEncoding,
                headers: headers,
                success: success,
                failure: failure)
    }
    
    func request(_ url: String,
                 method: HTTPMethod,
                 parameters: Parameters?,
                 encoding: ParameterEncoding,
                 headers: HTTPHeaders?,
                 success: @escaping (Data?) -> (),
                 failure: @escaping (NSError) -> () ) {
        
        var updatedParemeters: Parameters? = parameters
        if updatedParemeters == nil {
            updatedParemeters = ["api_key": self.apiKey]
        }
        else {
            updatedParemeters!["api_key"] = self.apiKey
        }
        
        self.request(self.serviceUrl(url),
                     method: method,
                     parameters: updatedParemeters,
                     encoding: encoding,
                     headers: headers).validate().response(completionHandler: { (response) in
                        
                        self.printResponse(response)
                        
                        if response.error == nil {
                            DispatchQueue.main.async {
                                if let _data = response.data {
                                    success(_data)
                                }
                                else {
                                    success(nil)
                                }
                            }
                        }
                        else {
                            
                            if let alamofireResponse = response.response, alamofireResponse.statusCode == 401 {
                                DispatchQueue.main.async {
                                    // Resource not Authenticated
                                    failure(NSError(domain: "Not Authenticated", code: 401, userInfo: nil))
                                }
                                return
                            }
                            
                            if let alamofireResponse = response.response, alamofireResponse.statusCode == 403 {
                                DispatchQueue.main.async {
                                    // Resource not found
                                    failure(NSError(domain: "Not Authorized", code: 403, userInfo: nil))
                                }
                                return
                            }
                            
                            if let alamofireResponse = response.response, alamofireResponse.statusCode == 404 {
                                DispatchQueue.main.async {
                                    // Resource not found
                                    failure(NSError(domain: "Not Found", code: 404, userInfo: nil))
                                }
                                return
                            }
                            
                            if let alamofireResponse = response.response,
                                alamofireResponse.statusCode <= 499 && alamofireResponse.statusCode >= 400 {
                                
                                if let data = response.data {
                                    
                                    do {
                                        if  let json = try JSONSerialization.jsonObject(with: data, options: []) as? NSDictionary {
                                            
                                            if let message = json["message"] as? NSArray {
                                                let userInfo = ["message": message]
                                                let error = NSError(domain: "Error", code: alamofireResponse.statusCode, userInfo: userInfo)
                                                DispatchQueue.main.async {
                                                    failure(error)
                                                }
                                            }
                                            return
                                        }
                                    }
                                    catch {
                                        if let message = String(data: data, encoding: .utf8) {
                                            let userInfo = ["message": message]
                                            let error = NSError(domain: "Error", code: alamofireResponse.statusCode, userInfo: userInfo)
                                            DispatchQueue.main.async {
                                                failure(error)
                                            }
                                            return
                                        }
                                    }
                                }
                            }
                            
                            if response.response?.statusCode == 500 {
                                DispatchQueue.main.async {
                                    if let message = response.error?.localizedDescription {
                                        let userInfo = ["message": message]
                                        failure(NSError(domain: "Error", code: 500, userInfo: userInfo))
                                    }
                                }
                                return
                            }
                            
                            DispatchQueue.main.async {
                                if let _error = response.error as NSError? {
                                    failure(_error)
                                }
                            }
                        }
                        
                     })
        
    }
    
}

