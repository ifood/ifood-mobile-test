//
//  Text.swift
//
//  Created by Ezequiel on 09/03/18
//  Copyright (c) . All rights reserved.
//

import Foundation
import ObjectMapper

public struct Text: Mappable {

  // MARK: Declaration for string constants to be used to decode and also serialize.
  private struct SerializationKeys {
    static let content = "content"
    static let beginOffset = "beginOffset"
  }

  // MARK: Properties
  public var content: String?
  public var beginOffset: Float?

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
    content <- map[SerializationKeys.content]
    beginOffset <- map[SerializationKeys.beginOffset]
  }
}
