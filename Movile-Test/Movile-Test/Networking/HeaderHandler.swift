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
    }

    func generate(header: HeaderType) -> [String: String] {
        switch header {
        case .basic:
            return self.getBasicHeader()
        }
    }

    private func getBasicHeader() -> [String: String] {
        return ["Content-Type": "application/json"]
    }

}
