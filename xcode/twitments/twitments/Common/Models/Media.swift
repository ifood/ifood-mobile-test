//
//  Media.swift
//
//  Created by Ezequiel on 08/03/18
//  Copyright (c) . All rights reserved.
//

import Foundation
import ObjectMapper

public struct Media: Mappable {

  // MARK: Declaration for string constants to be used to decode and also serialize.
  private struct SerializationKeys {
    static let mediaUrlHttps = "media_url_https"
    static let id = "id"
    static let expandedUrl = "expanded_url"
    static let mediaUrl = "media_url"
    static let idStr = "id_str"
    static let displayUrl = "display_url"
    static let type = "type"
    static let sizes = "sizes"
    static let url = "url"
    static let indices = "indices"
  }

  // MARK: Properties
  public var mediaUrlHttps: String?
  public var id: Int?
  public var expandedUrl: String?
  public var mediaUrl: String?
  public var idStr: String?
  public var displayUrl: String?
  public var type: String?
  public var sizes: Sizes?
  public var url: String?
  public var indices: [Int]?

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
    mediaUrlHttps <- map[SerializationKeys.mediaUrlHttps]
    id <- map[SerializationKeys.id]
    expandedUrl <- map[SerializationKeys.expandedUrl]
    mediaUrl <- map[SerializationKeys.mediaUrl]
    idStr <- map[SerializationKeys.idStr]
    displayUrl <- map[SerializationKeys.displayUrl]
    type <- map[SerializationKeys.type]
    sizes <- map[SerializationKeys.sizes]
    url <- map[SerializationKeys.url]
    indices <- map[SerializationKeys.indices]
  }
}
