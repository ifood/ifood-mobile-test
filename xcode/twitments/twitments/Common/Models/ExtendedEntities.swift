//
//  ExtendedEntities.swift
//
//  Created by Ezequiel on 08/03/18
//  Copyright (c) . All rights reserved.
//

import Foundation
import ObjectMapper

public struct ExtendedEntities: Mappable {

  // MARK: Declaration for string constants to be used to decode and also serialize.
  private struct SerializationKeys {
    static let media = "media"
  }

  // MARK: Properties
  public var media: [Media]?

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
    media <- map[SerializationKeys.media]
  }
}
