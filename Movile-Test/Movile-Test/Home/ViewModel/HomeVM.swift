//
//  HomeVM.swift
//  Movile-Test
//
//  Created by Thiago Felipe Alves on 14/07/19.
//  Copyright © 2019 Thalves. All rights reserved.
//

import UIKit

class HomeVM {

    var status: Observable<RequestStates<[Tweet]>> = Observable(RequestStates.loading)
    var tokenStatus: Observable<RequestStates<TwitterToken>> = Observable(RequestStates.loading)

    var list = [Tweet]()

    weak var delegate: HomeCoordinatorDelegate?

    var twitterToken: TwitterToken?

    func authenticate() {
        API().homeServices.twitterAuthentication(success: { auth in
            self.twitterToken = auth
            self.tokenStatus.value = .load(data: auth)
        }, failure: { error in
            self.tokenStatus.value = .errored(error: error)
        })
    }

    func listTweets(nickname: String) {

        guard let token = twitterToken?.token else {
            return
        }

        list.removeAll()

        API().homeServices.list(token: token, nickname: nickname, success: { tweets in
            self.list = tweets
            self.status.value = .load(data: tweets)
        }, failure: { error in

        })
    }

    func openDetail(tweet: Tweet) {
        self.delegate?.openDetail(tweet: tweet)
    }
}
