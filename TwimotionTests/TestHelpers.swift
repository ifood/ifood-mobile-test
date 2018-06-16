//
//  TestHelpers.swift
//  TwimotionTests
//
//  Created by Antony on 27/05/18.
//  Copyright © 2018 Twimotion. All rights reserved.
//

import Foundation

class TestHelper { }

func stub(_ resource: String) -> Data? {
    let bundle = Bundle(for: TestHelper.self)
    guard let url = bundle.url(forResource: resource, withExtension: "json") else {
        return nil
    }
    return try? Data(contentsOf: url)
}
