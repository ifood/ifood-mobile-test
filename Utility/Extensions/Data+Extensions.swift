//
//  Data+Extensions.swift
//  TwitterSentiment
//
//  Created by Jean Vinge on 20/06/2018.
//  Copyright © 2018 Atlas Politico. All rights reserved.
//

import Foundation

import UIKit

public protocol MockFile {
    var name: String { get }
}

public extension RawRepresentable where RawValue == String, Self: MockFile {
    var name: String {
        return self.rawValue
    }
}

extension Data {

    public static func data(with mock: MockFile, bundle: Bundle) -> Data {
        return Data(json: mock.name, bundle: bundle)
    }

    init(from resource: String, ext: String, bundle: Bundle) {
        guard let path = bundle.path(forResource: resource, ofType: ext) else {
            fatalError("path can't be nil")
        }
        do {
            try self.init(contentsOf: URL(fileURLWithPath: path), options: .mappedIfSafe)
        } catch let error {
            fatalError(error.localizedDescription)
        }
    }

    init(json filename: String, bundle: Bundle) {
        self.init(from: filename, ext: "json", bundle: bundle)
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
