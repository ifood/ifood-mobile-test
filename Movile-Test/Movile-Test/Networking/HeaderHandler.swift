//
//  HeaderHandler.swift
//  Movile-Test
//
//  Created by Thiago Felipe Alves on 14/07/19.
//  Copyright © 2019 Thalves. All rights reserved.
//

import UIKit

struct HeaderHandler {

    enum HeaderType {
        case basicTwitter
        case twitter(token: String)
    }

    func generate(header: HeaderType) -> [String: String] {
        switch header {
        case .basicTwitter:
            return self.getBasicTwitterHeader()
        case .twitter(token: let token):
            return self.getTwitterHeader(token: token)
        }
    }

    private func getBasicHeader() -> [String: String] {
        return ["Content-Type": "application/json"]
    }

    private func getBasicTwitterHeader() -> [String: String] {

        let twitterToken = "\(twitterApiKey):\(twitterApiSecretKey)".data(using: .utf8)?.base64EncodedString() ?? ""
        return ["Authorization": "Basic \(twitterToken)",
            "Content-Type": "application/x-www-form-urlencoded;charset=UTF-8"]
    }

    private func getTwitterHeader(token: String) -> [String: String] {
        return ["Authorization": "Bearer \(token)"]
    }

}
