//
//  Sentences.swift
//
//  Created by Ezequiel on 09/03/18
//  Copyright (c) . All rights reserved.
//

import Foundation
import ObjectMapper

public struct Sentences: Mappable {

  // MARK: Declaration for string constants to be used to decode and also serialize.
  private struct SerializationKeys {
    static let text = "text"
    static let sentiment = "sentiment"
  }

  // MARK: Properties
  public var text: Text?
  public var sentiment: Sentiment?

  // MARK: ObjectMapper Initializers
  /// Map a JSON object to this class using ObjectMapper.
  ///
  /// - parameter map: A mapping from ObjectMapper.
  public init?(map: Map) {

  }

  /// Map a JSON object to this class using ObjectMapper.
  ///
  /// - parameter map: A mapping from ObjectMapper.
  public mutating func mapping(map: Map) {
    text <- map[SerializationKeys.text]
    sentiment <- map[SerializationKeys.sentiment]
  }
}
