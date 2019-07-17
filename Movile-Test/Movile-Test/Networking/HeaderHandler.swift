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
        case basic
        case basicTwitter
        case twitter(token: String)
    }

    func generate(header: HeaderType) -> [String: String] {
        switch header {
        case .basic:
            return self.getBasicHeader()
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

        let twitterApiKey = "HGya2uFVPGYKndEuHSE1DPk0a"
        let twitterApiSecretKey = "9aFwDLJ0A3HiQYUN0EqhQam8YjqFr4uPTaAr2VucTXgIjjVnuX"
        
        let twitterToken = "\(twitterApiKey):\(twitterApiSecretKey)".data(using: .utf8)?.base64EncodedString() ?? ""
        return ["Authorization": "Basic \(twitterToken)",
            "Content-Type": "application/x-www-form-urlencoded;charset=UTF-8"]
    }

    private func getTwitterHeader(token: String) -> [String: String] {
        return ["Authorization": "Bearer \(token)"]
    }

}
