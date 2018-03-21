//
//  Urls.swift
//
//  Created by Ezequiel on 08/03/18
//  Copyright (c) . All rights reserved.
//

import Foundation
import ObjectMapper

public struct Urls: Mappable {

  // MARK: Declaration for string constants to be used to decode and also serialize.
  private struct SerializationKeys {
    static let displayUrl = "display_url"
    static let indices = "indices"
    static let expandedUrl = "expanded_url"
    static let url = "url"
  }

  // MARK: Properties
  public var displayUrl: String?
  public var indices: [Int]?
  public var expandedUrl: String?
  public var url: String?

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
    displayUrl <- map[SerializationKeys.displayUrl]
    indices <- map[SerializationKeys.indices]
    expandedUrl <- map[SerializationKeys.expandedUrl]
    url <- map[SerializationKeys.url]
  }
}
