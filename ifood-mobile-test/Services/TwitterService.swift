//
//  TwitterService.swift
//  ifood-mobile-test
//
//  Created by Marcio Garcia on 01/12/18.
//  Copyright © 2018 oxltech.com. All rights reserved.
//

import Foundation
import TwitterKit

class TwitterResponse {
    var userTimelineDataSource: TWTRUserTimelineDataSource!
}

class TwitterService {
    
    private var client: TWTRAPIClient!
    
    init() {
        TWTRTwitter.sharedInstance().start(withConsumerKey: "6xedE5uCFdUjmsVHM4j7efjuf", consumerSecret: "EOM8OkzipnP3elTh21W4UJtMnvOzpYYzJstYyTMiIqyMGFAl8n")
        client = TWTRAPIClient()
    }
    
    func requestUserTimeline(screenName: String) -> TwitterResponse {
        let dataSource = TWTRUserTimelineDataSource(screenName: screenName, apiClient: client)
        let response = TwitterResponse()
        response.userTimelineDataSource = dataSource
        return response
    }
    
}
