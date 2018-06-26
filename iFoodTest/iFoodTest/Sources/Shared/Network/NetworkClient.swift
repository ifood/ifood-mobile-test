//
//  NetworkClient.swift
//  iFoodTest
//
//  Created by Mauricio Cesar Maniglia Junior on 07/06/18.
//  Copyright © 2018 Mauricio Cesar Maniglia Junior. All rights reserved.
//

import Foundation

enum NetworkError: Error {
    case generic
    case invalidURL
}

protocol NetworkClientProtocol {
    func sendRequest(request: URLRequest, completion: @escaping (Data?, URLResponse?, Error?) -> ())
}

class NetworkClient: NetworkClientProtocol {
    
    static let sharedInstance = NetworkClient()
    let session: URLSession!
    
    init() {
        let configuration = URLSessionConfiguration.default
        configuration.urlCache = URLCache(memoryCapacity: 0, diskCapacity: 0, diskPath: nil)
        configuration.timeoutIntervalForRequest = 5.0
        session = URLSession(configuration: configuration)
    }
    
    func sendRequest(request: URLRequest, completion: @escaping (Data?, URLResponse?, Error?) -> ()) {
        
        guard let _ = request.url else {
            completion(nil, nil, NetworkError.invalidURL)
            return
        }
        
        session.dataTask(with: request) { data, response, error in
            DispatchQueue.main.async {
                completion(data, response, error)
            }
        }.resume()
    }
}
