//
//  TwitterUserContract.swift
//  iFoodTest
//
//  Created by Mauricio Cesar Maniglia Junior on 06/06/18.
//  Copyright © 2018 Mauricio Cesar Maniglia Junior. All rights reserved.
//

import UIKit

protocol TwitterUserBuilderProtocol {
    static func assembleModule() -> UIViewController
}

protocol TwitterUserRouterProtocol {    
    func showTweetsFor(user twitterUser: String)
}

protocol TwitterUserViewProtocol: class {
    var presenter: TwitterUserPresenterProtocol! { get set }
}

protocol TwitterUserPresenterProtocol: class {
    weak var view: TwitterUserViewProtocol? { get set }
    var router: TwitterUserRouterProtocol! { get set }
    
    func showTweetsFor(user twitterUser: String)
}
