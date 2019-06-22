//
//  NetworkDispatcher.swift
//  TwitterAnalyzer
//
//  Created by Bruno Vieira on 19/06/19.
//  Copyright © 2019 Bruno Vieira. All rights reserved.
//

import Foundation

protocol NetworkDispatcherProtocol {
    func requestArray<T>(of type: T.Type, request: RequestProtocol, completion: @escaping (([T]?, NetworkError?) -> Void)) where T : Decodable, T : Encodable
    func requestObject<T>(of type: T.Type, request: RequestProtocol, completion: @escaping ((T?, NetworkError?) -> Void)) where T : Decodable, T : Encodable
    func requestImage(from urlString: String, completion: @escaping ((Data?, NetworkError?) -> Void))
}

class NetworkDispatcher: NetworkDispatcherProtocol {
    private(set) var session: URLSession
    
    required init(session: URLSession = URLSession.shared) {
        self.session = session
    }
    
    func requestArray<T>(of type: T.Type, request: RequestProtocol, completion: @escaping (([T]?, NetworkError?) -> Void)) where T : Decodable, T : Encodable {
        let (taskRequest, error) = buildTaskRequestFromRequest(request)
        if let error = error {
            completion(nil, error)
            return
        }
        if let taskRequest = taskRequest {
            dispatch(request: taskRequest) { (responseData, error) in
                if let error = error {
                    completion(nil, error)
                    return
                } else {
                    guard let data = responseData, let arrayResponse = try? JSONDecoder().decode([T].self, from: data) else {
                        completion(nil, NetworkError(.serializationError))
                        return
                    }
                    completion(arrayResponse, nil)
                    return
                }
            }
        } else {
            completion(nil, NetworkError(.invalidRequest))
            return
        }
    }
    
    func requestObject<T>(of type: T.Type, request: RequestProtocol, completion: @escaping ((T?, NetworkError?) -> Void)) where T : Decodable, T : Encodable {
        let (taskRequest, error) = buildTaskRequestFromRequest(request)
        if let error = error {
            completion(nil, error)
            return
        }
        if let taskRequest = taskRequest {
            dispatch(request: taskRequest) { (responseData, error) in
                if let error = error {
                    completion(nil, error)
                    return
                } else {
                    guard let data = responseData, let objectResponse = try? JSONDecoder().decode(T.self, from: data) else {
                        completion(nil, NetworkError(.serializationError))
                        return
                    }
                    completion(objectResponse, nil)
                    return
                }
            }
        } else {
            completion(nil, NetworkError(.invalidRequest))
            return
        }
    }
    
    func requestImage(from urlString: String, completion: @escaping ((Data?, NetworkError?) -> Void)) {
        guard let url = URL(string: urlString) else {
            completion(nil, NetworkError(.invalidURL))
            return
        }
        URLSession.shared.dataTask(with: url, completionHandler: { data, response, error in
            guard
                let httpURLResponse = response as? HTTPURLResponse, httpURLResponse.statusCode == 200,
                let mimeType = response?.mimeType, mimeType.hasPrefix("image"),
                let data = data, error == nil
                else {
                    completion(nil, NetworkError(.invalidResponse))
                    return
            }
            completion(data, nil)
        }).resume()
    }
    
    func dispatch(request: URLRequest, completion: @escaping ((Data?, NetworkError?) -> Void)) {
        let task = session.dataTask(with: request) { (data, response, error) in
            guard let httpResponse = response as? HTTPURLResponse else {
                completion(nil, NetworkError(.invalidResponse))
                return
            }
            if let _ = error {
                completion(nil, NetworkError(.unknown))
                return
            }
            
            if httpResponse.statusCode == 404 {
                completion(nil, NetworkError(.urlNotFound))
                return
            }
            
            if !(200...299 ~= httpResponse.statusCode) {
                completion(nil, NetworkError(.unknown))
                return
            }
            
            if let data = data {
                completion(data, nil)
                return
            }
        }
        task.resume()
    }
}

// MARK: Helpers

extension NetworkDispatcher {
    func buildTaskRequestFromRequest(_ request: RequestProtocol) -> (URLRequest?, NetworkError?) {
        guard let url = URL(string: request.endpoint) else {
            return (nil, NetworkError(.invalidURL))
        }
        var taskRequest = URLRequest(url: url)
        taskRequest.httpMethod = request.method.rawValue
        if let headers = request.headerFields {
            for header in headers {
                taskRequest.addValue(header.value, forHTTPHeaderField: header.key)
            }
        }
        if let body = request.body {
            guard let dataBody = try? JSONSerialization.data(withJSONObject: body) else {
                return (nil, NetworkError(.invalidRequestBody))
            }
            taskRequest.httpBody = dataBody
        }
        return (taskRequest, nil)
    }
}
