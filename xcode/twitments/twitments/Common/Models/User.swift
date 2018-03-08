//
//  User.swift
//
//  Created by Ezequiel on 08/03/18
//  Copyright (c) . All rights reserved.
//

import Foundation
import ObjectMapper

public struct User: Mappable {

  // MARK: Declaration for string constants to be used to decode and also serialize.
  private struct SerializationKeys {
    static let translatorType = "translator_type"
    static let protected = "protected"
    static let profileSidebarBorderColor = "profile_sidebar_border_color"
    static let profileLinkColor = "profile_link_color"
    static let lang = "lang"
    static let favouritesCount = "favourites_count"
    static let defaultProfileImage = "default_profile_image"
    static let profileBackgroundColor = "profile_background_color"
    static let profileSidebarFillColor = "profile_sidebar_fill_color"
    static let profileBackgroundTile = "profile_background_tile"
    static let isTranslator = "is_translator"
    static let isTranslationEnabled = "is_translation_enabled"
    static let profileImageUrlHttps = "profile_image_url_https"
    static let friendsCount = "friends_count"
    static let id = "id"
    static let entities = "entities"
    static let profileBackgroundImageUrlHttps = "profile_background_image_url_https"
    static let utcOffset = "utc_offset"
    static let profileImageUrl = "profile_image_url"
    static let statusesCount = "statuses_count"
    static let defaultProfile = "default_profile"
    static let following = "following"
    static let url = "url"
    static let listedCount = "listed_count"
    static let name = "name"
    static let geoEnabled = "geo_enabled"
    static let profileUseBackgroundImage = "profile_use_background_image"
    static let screenName = "screen_name"
    static let descriptionValue = "description"
    static let notifications = "notifications"
    static let contributorsEnabled = "contributors_enabled"
    static let hasExtendedProfile = "has_extended_profile"
    static let followersCount = "followers_count"
    static let verified = "verified"
    static let location = "location"
    static let createdAt = "created_at"
    static let followRequestSent = "follow_request_sent"
    static let idStr = "id_str"
    static let profileBannerUrl = "profile_banner_url"
    static let profileBackgroundImageUrl = "profile_background_image_url"
    static let profileTextColor = "profile_text_color"
    static let timeZone = "time_zone"
  }

  // MARK: Properties
  public var translatorType: String?
  public var protected: Bool? = false
  public var profileSidebarBorderColor: String?
  public var profileLinkColor: String?
  public var lang: String?
  public var favouritesCount: Int?
  public var defaultProfileImage: Bool? = false
  public var profileBackgroundColor: String?
  public var profileSidebarFillColor: String?
  public var profileBackgroundTile: Bool? = false
  public var isTranslator: Bool? = false
  public var isTranslationEnabled: Bool? = false
  public var profileImageUrlHttps: String?
  public var friendsCount: Int?
  public var id: Int?
  public var profileBackgroundImageUrlHttps: String?
  public var utcOffset: Int?
  public var profileImageUrl: String?
  public var statusesCount: Int?
  public var defaultProfile: Bool? = false
  public var following: Bool? = false
  public var url: String?
  public var listedCount: Int?
  public var name: String?
  public var geoEnabled: Bool? = false
  public var profileUseBackgroundImage: Bool? = false
  public var screenName: String?
  public var descriptionValue: String?
  public var notifications: Bool? = false
  public var contributorsEnabled: Bool? = false
  public var hasExtendedProfile: Bool? = false
  public var followersCount: Int?
  public var verified: Bool? = false
  public var location: String?
  public var createdAt: String?
  public var followRequestSent: Bool? = false
  public var idStr: String?
  public var profileBannerUrl: String?
  public var profileBackgroundImageUrl: String?
  public var profileTextColor: String?
  public var timeZone: String?

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
    translatorType <- map[SerializationKeys.translatorType]
    protected <- map[SerializationKeys.protected]
    profileSidebarBorderColor <- map[SerializationKeys.profileSidebarBorderColor]
    profileLinkColor <- map[SerializationKeys.profileLinkColor]
    lang <- map[SerializationKeys.lang]
    favouritesCount <- map[SerializationKeys.favouritesCount]
    defaultProfileImage <- map[SerializationKeys.defaultProfileImage]
    profileBackgroundColor <- map[SerializationKeys.profileBackgroundColor]
    profileSidebarFillColor <- map[SerializationKeys.profileSidebarFillColor]
    profileBackgroundTile <- map[SerializationKeys.profileBackgroundTile]
    isTranslator <- map[SerializationKeys.isTranslator]
    isTranslationEnabled <- map[SerializationKeys.isTranslationEnabled]
    profileImageUrlHttps <- map[SerializationKeys.profileImageUrlHttps]
    friendsCount <- map[SerializationKeys.friendsCount]
    id <- map[SerializationKeys.id]
    profileBackgroundImageUrlHttps <- map[SerializationKeys.profileBackgroundImageUrlHttps]
    utcOffset <- map[SerializationKeys.utcOffset]
    profileImageUrl <- map[SerializationKeys.profileImageUrl]
    statusesCount <- map[SerializationKeys.statusesCount]
    defaultProfile <- map[SerializationKeys.defaultProfile]
    following <- map[SerializationKeys.following]
    url <- map[SerializationKeys.url]
    listedCount <- map[SerializationKeys.listedCount]
    name <- map[SerializationKeys.name]
    geoEnabled <- map[SerializationKeys.geoEnabled]
    profileUseBackgroundImage <- map[SerializationKeys.profileUseBackgroundImage]
    screenName <- map[SerializationKeys.screenName]
    descriptionValue <- map[SerializationKeys.descriptionValue]
    notifications <- map[SerializationKeys.notifications]
    contributorsEnabled <- map[SerializationKeys.contributorsEnabled]
    hasExtendedProfile <- map[SerializationKeys.hasExtendedProfile]
    followersCount <- map[SerializationKeys.followersCount]
    verified <- map[SerializationKeys.verified]
    location <- map[SerializationKeys.location]
    createdAt <- map[SerializationKeys.createdAt]
    followRequestSent <- map[SerializationKeys.followRequestSent]
    idStr <- map[SerializationKeys.idStr]
    profileBannerUrl <- map[SerializationKeys.profileBannerUrl]
    profileBackgroundImageUrl <- map[SerializationKeys.profileBackgroundImageUrl]
    profileTextColor <- map[SerializationKeys.profileTextColor]
    timeZone <- map[SerializationKeys.timeZone]
  }
}
