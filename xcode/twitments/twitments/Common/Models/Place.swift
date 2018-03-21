//
//  Place.swift
//
//  Created by Ezequiel on 08/03/18
//  Copyright (c) . All rights reserved.
//

import Foundation
import ObjectMapper

public struct Place: Mappable {

  // MARK: Declaration for string constants to be used to decode and also serialize.
  private struct SerializationKeys {
    static let containedWithin = "contained_within"
    static let name = "name"
    static let placeType = "place_type"
    static let attributes = "attributes"
    static let id = "id"
    static let fullName = "full_name"
    static let countryCode = "country_code"
    static let boundingBox = "bounding_box"
    static let url = "url"
    static let country = "country"
  }

  // MARK: Properties
  public var containedWithin: [Any]?
  public var name: String?
  public var placeType: String?
  public var attributes: Attributes?
  public var id: String?
  public var fullName: String?
  public var countryCode: String?
  public var boundingBox: BoundingBox?
  public var url: String?
  public var country: String?

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
    containedWithin <- map[SerializationKeys.containedWithin]
    name <- map[SerializationKeys.name]
    placeType <- map[SerializationKeys.placeType]
    attributes <- map[SerializationKeys.attributes]
    id <- map[SerializationKeys.id]
    fullName <- map[SerializationKeys.fullName]
    countryCode <- map[SerializationKeys.countryCode]
    boundingBox <- map[SerializationKeys.boundingBox]
    url <- map[SerializationKeys.url]
    country <- map[SerializationKeys.country]
  }
}
