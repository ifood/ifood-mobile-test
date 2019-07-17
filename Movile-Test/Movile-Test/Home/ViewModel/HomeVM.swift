//
//  HomeVM.swift
//  Movile-Test
//
//  Created by Thiago Felipe Alves on 14/07/19.
//  Copyright © 2019 Thalves. All rights reserved.
//

import UIKit

class HomeVM {

    var status: Observable<RequestStates<[Tweets]>> = Observable(RequestStates.loading)
    var list = [Tweets]()

    var twitterToken: TwitterToken?

    func authenticate() {
        API().homeServices.twitterAuthentication(success: { auth in
            self.twitterToken = auth
        }, failure: {_ in

        })
    }

    func listTweets(nickname: String) {

        list.removeAll()

        API().homeServices.list(token: twitterToken!.token, nickname: nickname, success: { tweets in
            self.list = tweets
            self.status.value = .load(data: tweets)
        }, failure: { _ in

        })
    }
}
