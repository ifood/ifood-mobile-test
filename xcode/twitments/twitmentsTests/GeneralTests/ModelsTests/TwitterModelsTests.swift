//
//  TwitterModelsTests.swift
//  twitmentsTests
//
//  Created by Ezequiel on 08/03/18.
//  Copyright © 2018 Ezequiel. All rights reserved.
//

import XCTest
import ObjectMapper
@testable import twitments

class TwitterModelsTests: XCTestCase {
    /*
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
     */
    func testDataGame() {
        let mock: [String: Any] = ["place": [
            "bounding_box": [
                "type": "Polygon",
                "coordinates": [[[-46.677895, -23.55238],
                                 [-46.677895, -23.55238],
                                 [-46.677895, -23.55238],
                                 [-46.677895, -23.55238]]]
            ],
            "contained_within": [],
            "country": "Brasil",
            "id": "0c8820caf396b000",
            "attributes": [:],
            "place_type": "poi",
            "full_name": "E-Commerce Brasil Hub​",
            "country_code": "BR",
            "name": "E-Commerce Brasil Hub​",
            "url": "https:\\/\\/api.twitter.com\\/1.1\\/geo\\/id\\/0c8820caf396b000.json"
            ], "contributors": "null", "source": "<a href=\"https:\\/\\/www.producthunt.com\" rel=\"nofollow\">Product Hunt<\\/a>",
               "truncated": false, "is_quote_status": false,
               "favorite_count": 1, "possibly_sensitive": false,
               "lang": "en",
               "entities": [
                "media": [
                    [
                        "id_str": "943449534742581249",
                        "media_url_https": "https:\\/\\/pbs.twimg.com\\/media\\/DRfOWm3W0AEbRHU.jpg",
                        "expanded_url": "https:\\/\\/twitter.com\\/uncareniterp\\/status\\/943449538739736576\\/photo\\/1",
                        "id": 943449534742581249,
                        "sizes": [
                            "large": [
                                "w": 1024,
                                "h": 512,
                                "resize": "fit"
                            ],
                            "medium": [
                                "w": 1024,
                                "h": 512,
                                "resize": "fit"
                            ],
                            "thumb": [
                                "w": 150,
                                "h": 150,
                                "resize": "crop"
                            ],
                            "small": [
                                "w": 680,
                                "h": 340,
                                "resize": "fit"
                            ]
                        ],
                        "display_url": "pic.twitter.com\\/ecHwkIuW5l",
                        "type": "photo",
                        "indices": [
                            101,
                            124
                        ],
                        "media_url": "http:\\/\\/pbs.twimg.com\\/media\\/DRfOWm3W0AEbRHU.jpg",
                        "url": "https:\\/\\/t.co\\/ecHwkIuW5l"
                    ]
                ],
                "symbols": [

                ],
                "user_mentions": [
                    [
                        "id_str": "291536063",
                        "id": 291536063,
                        "screen_name": "ezefranca",
                        "name": "Ezequiel França",
                        "indices": [
                            90,
                            100
                        ]
                    ]
                ],
                "urls": [
                    [
                        "display_url": "producthunt.com\\/posts\\/watchnote",
                        "url": "https:\\/\\/t.co\\/HcbN7IeJjN",
                        "indices": [
                            62,
                            85
                        ],
                        "expanded_url": "https:\\/\\/www.producthunt.com\\/posts\\/watchnote"
                    ]
                ],
                "hashtags": ["indices": [0],
                             "text": "#twitter"
                ]
            ],
               "in_reply_to_screen_name": "null",
               "retweet_count": 0,
               "favorited": true,
               "geo": "null",
               "id": 943449538739736576,
               "user": [
                "protected": false,
                "is_translator": false,
                "profile_image_url": "http:\\/\\/pbs.twimg.com\\/profile_images\\/789854776129355776\\/55JjQ85o_normal.jpg",
                "created_at": "Wed Mar 31 02:35:49 +0000 2010",
                "id": 128092817,
                "default_profile_image": false,
                "listed_count": 2,
                "profile_background_color": "1A1B1F",
                "follow_request_sent": false,
                "location": "Chicago, IL",
                "entities": [
                    "description": [
                        "urls": [

                        ]
                    ]
                ],
                "url": nil,
                "description": "",
                "followers_count": 87,
                "geo_enabled": false,
                "lang": "en",
                "profile_text_color": "666666",
                "statuses_count": 7,
                "following": false,
                "notifications": false,
                "profile_background_tile": false,
                "profile_use_background_image": true,
                "id_str": "128092817",
                "name": "Ronda Lewis",
                "profile_image_url_https": "https:\\/\\/pbs.twimg.com\\/profile_images\\/789854776129355776\\/55JjQ85o_normal.jpg",
                "profile_sidebar_fill_color": "252429",
                "profile_sidebar_border_color": "181A1E",
                "contributors_enabled": false,
                "default_profile": false,
                "profile_banner_url": "https:\\/\\/pbs.twimg.com\\/profile_banners\\/128092817\\/1477151050",
                "screen_name": "uncareniterp",
                "time_zone": "Arizona",
                "profile_background_image_url": "http:\\/\\/abs.twimg.com\\/images\\/themes\\/theme9\\/bg.gif",
                "profile_background_image_url_https": "https:\\/\\/abs.twimg.com\\/images\\/themes\\/theme9\\/bg.gif",
                "profile_link_color": "2FC2EF",
                "favourites_count": 0,
                "is_translation_enabled": false,
                "translator_type": "none",
                "utc_offset": -25200,
                "friends_count": 252,
                "verified": false,
                "has_extended_profile": false
            ],
               "in_reply_to_user_id": 2018,
               "extended_entities": [
                "media": [
                    [
                        "id_str": "943449534742581249",
                        "media_url_https": "https:\\/\\/pbs.twimg.com\\/media\\/DRfOWm3W0AEbRHU.jpg",
                        "expanded_url": "https:\\/\\/twitter.com\\/uncareniterp\\/status\\/943449538739736576\\/photo\\/1",
                        "id": 943449534742581249,
                        "sizes": [
                            "large": [
                                "w": 1024,
                                "h": 512,
                                "resize": "fit"
                            ],
                            "medium": [
                                "w": 1024,
                                "h": 512,
                                "resize": "fit"
                            ],
                            "thumb": [
                                "w": 150,
                                "h": 150,
                                "resize": "crop"
                            ],
                            "small": [
                                "w": 680,
                                "h": 340,
                                "resize": "fit"
                            ]
                        ],
                        "display_url": "pic.twitter.com\\/ecHwkIuW5l",
                        "type": "photo",
                        "indices": [
                            101,
                            124
                        ],
                        "media_url": "http:\\/\\/pbs.twimg.com\\/media\\/DRfOWm3W0AEbRHU.jpg",
                        "url": "https:\\/\\/t.co\\/ecHwkIuW5l"
                    ]
                ]
            ],
               "retweeted": false,
               "text": "WatchNote: ⌚️ 🤓 Control your Keynote slides using Apple Watch https:\\/\\/t.co\\/HcbN7IeJjN via @ezefranca https:\\/\\/t.co\\/ecHwkIuW5l",
               "created_at": "Wed Dec 20 11:54:33 +0000 2017",
               "in_reply_to_status_id_str": "2018",
               "in_reply_to_status_id": 2018,
               "in_reply_to_user_id_str": "null",
               "id_str": "943449538739736576",
               "coordinates": "null"
        ]
        let resultMock = Mapper<TwitterResult>().map(JSON: mock)
        XCTAssertNotNil(resultMock?.inReplyToStatusId)
        XCTAssertNotNil(resultMock?.isQuoteStatus)
        XCTAssertNotNil(resultMock?.source)
        XCTAssertNotNil(resultMock?.lang)
        XCTAssertNotNil(resultMock?.place)
        XCTAssertNotNil(resultMock?.retweetCount)
        XCTAssertNotNil(resultMock?.favorited)
        XCTAssertNotNil(resultMock?.inReplyToScreenName)
        XCTAssertNotNil(resultMock?.id)
        XCTAssertNotNil(resultMock?.retweetCount)
        XCTAssertNotNil(resultMock?.text)
        XCTAssertNotNil(resultMock?.inReplyToUserId)
        XCTAssertNotNil(resultMock?.user)
        XCTAssertNotNil(resultMock?.favoriteCount)
        XCTAssertNotNil(resultMock?.possiblySensitive)
        XCTAssertNotNil(resultMock?.createdAt)
        XCTAssertNotNil(resultMock?.idStr)
        XCTAssertNotNil(resultMock?.extendedEntities)
        XCTAssertNotNil(resultMock?.truncated)
        XCTAssertNotNil(resultMock?.inReplyToUserIdStr)
        XCTAssertNotNil(resultMock?.favoriteCount)
        XCTAssertNotNil(resultMock?.possiblySensitive)
        XCTAssertNotNil(resultMock?.createdAt)

    }

}
