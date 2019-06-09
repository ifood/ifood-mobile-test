//
//  ResponseDataSpec.swift
//  IfoodMobileTestTests
//
//  Created by André Vieira on 09/06/19.
//  Copyright © 2019 André Vieira. All rights reserved.
//

import Foundation
import Quick
import Nimble

@testable import IfoodMobileTest

struct TestObject: Codable {
    var type: String?
    var text: String?
}

class ResponseDataSpec: QuickSpec {
    var object: TestObject?
    var array: [TestObject]?
    override func spec() {
        describe("Response data") {
            context("Parse data") {
                it("Object") {
                    let data = try? JSONSerialization.data(withJSONObject: ["type": "Bearer",
                                                                           "text" : "AAAAAAAAAAAAAAAAAAAAA"],
                                                           options: .prettyPrinted)
                    self.object = try? data?.mapToObject()
                    expect(self.object?.type).to(equal("Bearer"))
                    expect(self.object?.text).to(equal("AAAAAAAAAAAAAAAAAAAAA"))
                }
                
                it("Array") {
                    let data = try? JSONSerialization.data(withJSONObject: [["type": "Bearer",
                                                                            "text" : "AAAAAAAAAAAAAAAAAAAAA"],
                                                                            ["type": "Bearer",
                                                                             "text" : "BBBBBBBBBBBBBBBBBBBB"]],
                                                           options: .prettyPrinted)
                    self.array = try? data?.mapToArray()
                    expect(self.array?.count).to(equal(2))
                    expect(self.array?[1].text).to(equal("BBBBBBBBBBBBBBBBBBBB"))
                }
            }
        }
    }
}
