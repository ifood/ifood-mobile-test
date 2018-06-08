//
//  TwitterUserPresenter.swift
//  iFoodTest
//
//  Created by Mauricio Cesar Maniglia Junior on 06/06/18.
//  Copyright © 2018 Mauricio Cesar Maniglia Junior. All rights reserved.
//

import Foundation

final class TwitterUserPresenter: TwitterUserPresenterProtocol {
    
    var view: TwitterUserViewProtocol?
    var router: TwitterUserRouterProtocol!
    
    func showTweetsFor(user twitterUser: String) {
        router.presentTweetListFor(user: twitterUser)
    }
}
