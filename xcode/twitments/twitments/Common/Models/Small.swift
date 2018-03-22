//
//  Small.swift
//
//  Created by Ezequiel on 08/03/18
//  Copyright (c) . All rights reserved.
//

import Foundation
import ObjectMapper

public struct Small: Mappable {

  // MARK: Declaration for string constants to be used to decode and also serialize.
  private struct SerializationKeys {
    static let resize = "resize"
    static let h = "h"
    static let w = "w"
  }

  // MARK: Properties
  public var resize: String?
  public var h: Int?
  public var w: Int?

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
    resize <- map[SerializationKeys.resize]
    h <- map[SerializationKeys.h]
    w <- map[SerializationKeys.w]
  }
}
