//
//  DetailViewModel.swift
//  Movile-Test
//
//  Created by talves on 17/07/19.
//  Copyright © 2019 Thalves. All rights reserved.
//

import UIKit

class DetailVM {

    // MARK: - Properties
    var status: Observable<RequestStates<Sentiment>> = Observable(RequestStates.loading)
    let tweet: Tweet

    // MARK: - Initialization
    init(tweet: Tweet) {
        self.tweet = tweet

        analyze(text: tweet.text)
    }

    func analyze(text: String) {
        API().sentimentServices.analyze(text: text, success: { sentiment in
            self.status.value = .load(data: sentiment)
        }, failure: { error in
            self.status.value = .errored(error: error)
        })
    }
}
