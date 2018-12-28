//
//  Decoder.swift
//  TwitterSentimentTests
//
//  Created by Jean Vinge on 28/12/18.
//  Copyright © 2018 Jean Vinge. All rights reserved.
//

import Foundation
import Utility
import Domain

func decode<T: Codable>(_ classType: T.Type, from mock: MockFile, bundle: Bundle) -> T {
    do {
        return try JSONDecoder.decode(classType, from: mock, bundle: bundle)
    } catch {
        fatalError("\(classType) can't be nil")
    }
}

func twitterError(_ mock: MockFile, bundle: Bundle) -> Error {
    do {
        let errors = try JSONDecoder.decode(TwitterErrors.self, from: mock, bundle: bundle)
        return TwitterMoyaError.testError(errors)
    } catch {
        fatalError("Error can't be nil")
    }
}
