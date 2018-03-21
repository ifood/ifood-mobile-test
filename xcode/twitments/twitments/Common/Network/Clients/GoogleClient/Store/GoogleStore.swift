//
//  GoogleStore.swift
//  twitments
//
//  Created by Ezequiel on 08/03/18.
//  Copyright © 2018 Ezequiel. All rights reserved.
//

import Foundation
import UIKit
import Alamofire

protocol GoogleStore: AbstractStore {
    func sentimentsOf(tweet: String, completion: @escaping Completion<DocumentSentiment?>)
}

class GoogleAdapter: RequestAdapter {

    fileprivate let token: String

    init(_ token: String) {
        self.token = token
    }

    func adapt(_ urlRequest: URLRequest) throws -> URLRequest {
        var urlRequest = urlRequest
        guard let url = urlRequest.url else { return urlRequest }
        var urlComponents = URLComponents(url: url, resolvingAgainstBaseURL: true)
        urlComponents?.queryItems?.append(URLQueryItem(name: "key", value: token))
        urlRequest.url = urlComponents?.url
        return urlRequest
    }
}
