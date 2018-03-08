//
//  Coordinates.swift
//
//  Created by Ezequiel on 08/03/18
//  Copyright (c) . All rights reserved.
//

import Foundation
import ObjectMapper

public struct Coordinates: Mappable {

  // MARK: Declaration for string constants to be used to decode and also serialize.
  private struct SerializationKeys {
    static let two = "2"
    static let one = "1"
    static let zero = "0"
    static let three = "3"
  }

  // MARK: Properties
  public var two: [Float]?
  public var one: [Float]?
  public var zero: [Float]?
  public var three: [Float]?

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
    two <- map[SerializationKeys.two]
    one <- map[SerializationKeys.one]
    zero <- map[SerializationKeys.zero]
    three <- map[SerializationKeys.three]
  }
}
