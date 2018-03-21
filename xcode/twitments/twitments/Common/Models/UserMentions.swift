//
//  UserMentions.swift
//
//  Created by Ezequiel on 08/03/18
//  Copyright (c) . All rights reserved.
//

import Foundation
import ObjectMapper

public struct UserMentions: Mappable {

  // MARK: Declaration for string constants to be used to decode and also serialize.
  private struct SerializationKeys {
    static let idStr = "id_str"
    static let name = "name"
    static let id = "id"
    static let screenName = "screen_name"
    static let indices = "indices"
  }

  // MARK: Properties
  public var idStr: String?
  public var name: String?
  public var id: Int?
  public var screenName: String?
  public var indices: [Int]?

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
    idStr <- map[SerializationKeys.idStr]
    name <- map[SerializationKeys.name]
    id <- map[SerializationKeys.id]
    screenName <- map[SerializationKeys.screenName]
    indices <- map[SerializationKeys.indices]
  }
}
