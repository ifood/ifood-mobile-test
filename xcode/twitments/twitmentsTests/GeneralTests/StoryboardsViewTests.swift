//
//  StoryboardsViewTests.swift
//  twitmentsTests
//
//  Created by Ezequiel on 08/03/18.
//  Copyright © 2018 Ezequiel. All rights reserved.
//

import XCTest
import EFInternetIndicator
@testable import twitments

class StoryboardTests: XCTestCase {

    var addHandler: AddHandlerViewController?
    var abstract: AbstractViewController?

    override func setUp() {
        super.setUp()
        let storyboard = UIStoryboard(name: "Main", bundle: nil)
        self.addHandler = storyboard.instantiateViewController(withIdentifier: "AddHandlerViewController") as? AddHandlerViewController
        self.addHandler?.viewDidLoad()
    }

    override func tearDown() {
        super.tearDown()
    }

    func testViewController() {
        XCTAssertNotEqual(self.addHandler, nil)
    }

    func testConnectionMonitor() {
        self.abstract = self.addHandler
        XCTAssertNotNil(abstract?.internetConnectionIndicator)
    }
}
