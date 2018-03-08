//
//  TwitterResult.swift
//
//  Created by Ezequiel on 08/03/18
//  Copyright (c) . All rights reserved.
//

import Foundation
import ObjectMapper

public struct TwitterResult: Mappable {

  // MARK: Declaration for string constants to be used to decode and also serialize.
  private struct SerializationKeys {
    static let inReplyToStatusId = "in_reply_to_status_id"
    static let isQuoteStatus = "is_quote_status"
    static let source = "source"
    static let lang = "lang"
    static let place = "place"
    static let retweetCount = "retweet_count"
    static let favorited = "favorited"
    static let inReplyToScreenName = "in_reply_to_screen_name"
    static let id = "id"
    static let retweeted = "retweeted"
    static let text = "text"
    static let entities = "entities"
    static let inReplyToUserId = "in_reply_to_user_id"
    static let user = "user"
    static let favoriteCount = "favorite_count"
    static let possiblySensitive = "possibly_sensitive"
    static let createdAt = "created_at"
    static let inReplyToUserIdStr = "in_reply_to_user_id_str"
    static let idStr = "id_str"
    static let extendedEntities = "extended_entities"
    static let truncated = "truncated"
    static let inReplyToStatusIdStr = "in_reply_to_status_id_str"
  }

  // MARK: Properties
  public var inReplyToStatusId: Int?
  public var isQuoteStatus: Bool? = false
  public var source: String?
  public var lang: String?
  public var place: Place?
  public var retweetCount: Int?
  public var favorited: Bool? = false
  public var inReplyToScreenName: String?
  public var id: Int?
  public var retweeted: Bool? = false
  public var text: String?
  public var entities: Entities?
  public var inReplyToUserId: Int?
  public var user: User?
  public var favoriteCount: Int?
  public var possiblySensitive: Bool? = false
  public var createdAt: String?
  public var inReplyToUserIdStr: String?
  public var idStr: String?
  public var extendedEntities: ExtendedEntities?
  public var truncated: Bool? = false
  public var inReplyToStatusIdStr: String?

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
    inReplyToStatusId <- map[SerializationKeys.inReplyToStatusId]
    isQuoteStatus <- map[SerializationKeys.isQuoteStatus]
    source <- map[SerializationKeys.source]
    lang <- map[SerializationKeys.lang]
    place <- map[SerializationKeys.place]
    retweetCount <- map[SerializationKeys.retweetCount]
    favorited <- map[SerializationKeys.favorited]
    inReplyToScreenName <- map[SerializationKeys.inReplyToScreenName]
    id <- map[SerializationKeys.id]
    retweeted <- map[SerializationKeys.retweeted]
    text <- map[SerializationKeys.text]
    entities <- map[SerializationKeys.entities]
    inReplyToUserId <- map[SerializationKeys.inReplyToUserId]
    user <- map[SerializationKeys.user]
    favoriteCount <- map[SerializationKeys.favoriteCount]
    possiblySensitive <- map[SerializationKeys.possiblySensitive]
    createdAt <- map[SerializationKeys.createdAt]
    inReplyToUserIdStr <- map[SerializationKeys.inReplyToUserIdStr]
    idStr <- map[SerializationKeys.idStr]
    extendedEntities <- map[SerializationKeys.extendedEntities]
    truncated <- map[SerializationKeys.truncated]
    inReplyToStatusIdStr <- map[SerializationKeys.inReplyToStatusIdStr]
  }
}
