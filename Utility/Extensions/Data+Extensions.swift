//
//  Data+Extensions.swift
//  TwitterSentiment
//
//  Created by Jean Vinge on 20/06/2018.
//  Copyright © 2018 Atlas Politico. All rights reserved.
//

import Foundation

extension Bundle {
    static var correctBundle: Bundle? {
        return Bundle.main
    }
}

extension Data {

    public static func data(with mock: MockFile) -> Data {
        return Data(json: mock.rawValue)
    }

    init(from resource: String, ext: String) {
        guard let path = Bundle.correctBundle?.path(forResource: resource, ofType: ext) else {
            fatalError("path can't be nil")
        }
        do {
            try self.init(contentsOf: URL(fileURLWithPath: path), options: .mappedIfSafe)
        } catch let error {
            fatalError(error.localizedDescription)
        }
    }

    init(json filename: String) {
        self.init(from: filename, ext: "json")
    }

    public static var emptyJSON: Data {
        return "{}".data(using: .utf8)!
    }

    public func JSONPretty(data: Data) -> Data {
        do {
            return try JSONSerialization.data(withJSONObject: try JSONSerialization.jsonObject(with: data), options: .prettyPrinted)
        } catch {
            return data
        }
    }
}
