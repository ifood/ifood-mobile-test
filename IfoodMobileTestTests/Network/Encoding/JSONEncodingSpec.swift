//
//  JSONEncodingSpec.swift
//  IfoodMobileTestTests
//
//  Created by André Vieira on 10/06/19.
//  Copyright © 2019 André Vieira. All rights reserved.
//

import Foundation
import Quick
import Nimble

@testable import IfoodMobileTest

class JSONEncodingSpec: QuickSpec {
    
    var param: [String: Any] = [:]
    var request = URLRequest(url: URL(string: "https://testurlencoding.com")!)
    var jsonEncoding = JSONEncoding.default
    
    override func spec() {
        describe("JSONEncoding") {
            context("to be not nil") {
                beforeEach {
                    self.param = [:]
                    self.request.httpMethod = "POST"
                }
                
                it("Simple Parameters") {
                    self.param = ["grant_type": "client_credentials"]
                    let requestEncoded = try? self.jsonEncoding.encode(request: self.request, parameters: self.param)
                   
                    let json = try? JSONSerialization.jsonObject(with: requestEncoded?.httpBody ?? Data(), options: []) as? NSDictionary
                    expect(json?["grant_type"] as? String).to(equal("client_credentials"))
                }
                
                it("when parameters to be nil") {
                    let requestEncoded = try? self.jsonEncoding.encode(request: self.request, parameters: nil)
                    expect(requestEncoded?.httpBody).to(beNil())
                }
            }
        }
    }
}
