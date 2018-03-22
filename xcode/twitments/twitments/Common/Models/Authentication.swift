//
//  Authentication.swift
//
//  Created by Ezequiel on 08/03/18
//  Copyright (c) . All rights reserved.
//

import Foundation
import ObjectMapper

public struct Authentication: Mappable {

  // MARK: Declaration for string constants to be used to decode and also serialize.
  private struct SerializationKeys {
    static let tokenType = "token_type"
    static let accessToken = "access_token"
  }

  // MARK: Properties
  public var tokenType: String?
  public var accessToken: String?

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
    tokenType <- map[SerializationKeys.tokenType]
    accessToken <- map[SerializationKeys.accessToken]
  }

}
