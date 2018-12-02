//
//  TimelineInteractor.swift
//  ifood-mobile-test
//
//  Created by Marcio Garcia on 01/12/18.
//  Copyright (c) 2018 oxltech.com. All rights reserved.
//
//  This file was generated by the Clean Swift Xcode Templates so
//  you can apply clean architecture to your iOS and Mac projects,
//  see http://clean-swift.com
//

import UIKit

protocol TimelineBusinessLogic {
    func fetchTwitterUserTimeline(request: Timeline.FetchTimeline.Request)
    func storeTweetText(text: String)
}

protocol TimelineDataStore {
    var tweetText: String { get set }
}

class TimelineInteractor: TimelineBusinessLogic, TimelineDataStore {
    
    var presenter: TimelinePresentationLogic?
    var worker: TimelineWorker?
    var tweetText: String = ""

    // MARK: Do something

    func fetchTwitterUserTimeline(request: Timeline.FetchTimeline.Request) {
        worker = TimelineWorker()
        if let twitterResponse = worker?.requestTwitterUserTimeline(screenName: request.screenName) {
            if twitterResponse.userTimelineDataSource == nil {
                let response = Timeline.Error.Response(code: 999, message: NSLocalizedString("Tweets not found", comment: ""))
                presenter?.presentError(response: response)
            }
            else {
                let response = Timeline.FetchTimeline.Response(twitterResponse: twitterResponse)
                presenter?.presentTwitterUserTimeline(response: response)
            }
        }
    }
    
    func storeTweetText(text: String) {
        tweetText = text
    }
    
}
