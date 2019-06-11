//
//  URLEncodingSpec.swift
//  IfoodMobileTestTests
//
//  Created by André Vieira on 09/06/19.
//  Copyright © 2019 André Vieira. All rights reserved.
//

import Foundation
import Quick
import Nimble

@testable import IfoodMobileTest

class URLEncodingSpec: QuickSpec {
    
    var param: [String: Any] = [:]
    var request = URLRequest(url: URL(string: "https://testurlencoding.com")!)
    var urlEncoding = URLEncoding.default
    
    override func spec() {
        describe("URLEncoding") {
            context("encoding") {
                beforeEach {
                    self.param = [:]
                    self.request.httpMethod = "POST"
                }
                
                it("Simple Parameters") {
                    self.param = ["grant_type": "client_credentials"]
                    let requestEncoded = try? self.urlEncoding.encode(request: self.request, parameters: self.param)
                    expect(requestEncoded?.url?.absoluteString).to(equal("https://testurlencoding.com?grant_type=client_credentials"))
                }
                
                it("parameters with special characters") {
                    self.param = ["name": "André @*% Vieira"]
                    let requestEncoded = try? self.urlEncoding.encode(request: self.request, parameters: self.param)
                    expect(requestEncoded?.url?.absoluteString).to(equal("https://testurlencoding.com?name=Andr%C3%A9%20%40%2A%25%20Vieira"))
                }
                
                it("when parameters to be nil") {
                    let requestEncoded = try? self.urlEncoding.encode(request: self.request, parameters: nil)
                    expect(requestEncoded?.url?.absoluteString).to(equal("https://testurlencoding.com"))
                }
            }
        }
    }
}
