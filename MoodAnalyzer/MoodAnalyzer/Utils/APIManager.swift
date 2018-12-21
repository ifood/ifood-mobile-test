//
//  APIManager.swift
//  MoodAnalyzer
//
//  Created by Carlos Henrique Salvador on 14/12/18.
//  Copyright © 2018 Carlos Henrique Salvador. All rights reserved.
//

import Foundation
import JNKeychain

class APIManager {
    
    static func buildQueryString(fromDictionary parameters: [String:String]) -> String {
        var urlVars:[String] = []
        
        for (k,value) in parameters {
            if let encodedValue = value.addingPercentEncoding(withAllowedCharacters: CharacterSet.urlQueryAllowed) {
                urlVars.append(k + "=" + encodedValue)
            }
        }
        
        return urlVars.isEmpty ? "" : "?" + urlVars.joined(separator: "&")
    }
    
    static func getDefaultHeaders() -> [String:String] {
        return ["Authorization" : "Bearer \(JNKeychain.loadValue(forKey: "Bearer") ?? "")",
            "Content-Type" : "application/json"]
    }
    
    static func getPostString(params:[String:Any]) -> String {
        var data = [String]()
        for(key, value) in params
        {
            data.append(key + "=\(value)")
        }
        return data.map { String($0) }.joined(separator: "&")
    }
    
    static func getBearer() {
        
        let encodedConsumerKey = Constants.kTwitterAPIKey.RTF1738encode()
        let encodedSecretKey = Constants.kTwitterAPISecretKey.RTF1738encode()
        
        let joinedKeys = "\(encodedConsumerKey):\(encodedSecretKey)".toBase64()
        
        let params = ["grant_type" : "client_credentials"]
        
        let headers = ["Authorization" : "Basic \(joinedKeys)",
            "Content-Type" : "application/x-www-form-urlencoded;charset=UTF-8"]
        
        self.postToTwitterAPI(service: Constants.kTwitterToken, params: params, headers: headers) { response, error in
            if let resp = response, let bearer = resp["access_token"] as? String {
               JNKeychain.saveValue(bearer, forKey: "Bearer")
            } else {
                // SHOW POP UP
            }
        }
    }
    
    static func postToTwitterAPI(service: String, params: [String : Any], headers: [String : Any], completion: @escaping ([String : Any]?, Error?) -> Void) {
        
        guard let url = URL(string: Constants.kTwitterBaseURL + service) else {
            return
        }
        
        var request = URLRequest(url: url)
        request.httpMethod = "POST"

        let postString = self.getPostString(params: params)
        request.httpBody = postString.data(using: .utf8)
        
        // Setting Headers
        for key in headers.keys {
            if let value = headers[key] as? String {
                request.setValue(value, forHTTPHeaderField: "\(key)")
            }
        }
        
        let task = URLSession.shared.dataTask(with: request) { data, response, error in
            guard let data = data, error == nil else {
                completion(nil, error)
                print(error?.localizedDescription ?? "No data")
                return
            }
            let responseJSON = try? JSONSerialization.jsonObject(with: data, options: [])
            if let responseJSON = responseJSON as? [String: Any] {
                completion(responseJSON, nil)
            }
        }
        
        task.resume()
    }
    
    static func getFromTwitterAPI(service: String, params: [String : String], headers: [String : Any], completion: @escaping (Data?, Any?, Error?) -> Void) {
        
        
        guard let url = URL(string: Constants.kTwitterBaseURL + service + self.buildQueryString(fromDictionary: params)) else {
            return
        }
        
        var request = URLRequest(url: url)
        request.httpMethod = "GET"
        
//        let postString = self.getPostString(params: params)
//        request.httpBody = postString.data(using: .utf8)
        
        
        
        // Setting Headers
        for key in headers.keys {
            if let value = headers[key] as? String {
                request.setValue(value, forHTTPHeaderField: "\(key)")
            }
        }
        
        let task = URLSession.shared.dataTask(with: request) { data, response, error in
            guard let data = data, error == nil else {
                completion(nil, nil, error)
                print(error?.localizedDescription ?? "No data")
                return
            }
            completion(data, response, nil)
        }
        
        task.resume()
    }
    
}


