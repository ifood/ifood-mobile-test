//
//  DocumentSentiment.swift
//
//  Created by Ezequiel on 09/03/18
//  Copyright (c) . All rights reserved.
//

import Foundation
import ObjectMapper

public struct DocumentSentiment: Mappable {

  // MARK: Declaration for string constants to be used to decode and also serialize.
  private struct SerializationKeys {
    static let magnitude = "magnitude"
    static let score = "score"
  }

  // MARK: Properties
  public var magnitude: Float?
  public var score: Float?

  // MARK: ObjectMapper Initializers
  /// Map a JSON object to this class using ObjectMapper.
  ///
  /// - parameter map: A mapping from ObjectMapper.
  public init?(map: Map){

  }

  /// Map a JSON object to this class using ObjectMapper.
  ///
  /// - parameter map: A mapping from ObjectMapper.
  public mutating func mapping(map: Map) {
    magnitude <- map[SerializationKeys.magnitude]
    score <- map[SerializationKeys.score]
  }
}
