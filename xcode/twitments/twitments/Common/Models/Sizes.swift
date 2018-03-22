//
//  Sizes.swift
//
//  Created by Ezequiel on 08/03/18
//  Copyright (c) . All rights reserved.
//

import Foundation
import ObjectMapper

public struct Sizes: Mappable {

  // MARK: Declaration for string constants to be used to decode and also serialize.
  private struct SerializationKeys {
    static let medium = "medium"
    static let small = "small"
    static let thumb = "thumb"
    static let large = "large"
  }

  // MARK: Properties
  public var medium: Medium?
  public var small: Small?
  public var thumb: Thumb?
  public var large: Large?

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
    medium <- map[SerializationKeys.medium]
    small <- map[SerializationKeys.small]
    thumb <- map[SerializationKeys.thumb]
    large <- map[SerializationKeys.large]
  }
}
