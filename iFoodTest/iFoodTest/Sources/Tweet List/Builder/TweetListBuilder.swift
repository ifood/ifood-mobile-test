//
//  TweetListBuilder.swift
//  iFoodTest
//
//  Created by Mauricio Cesar Maniglia Junior on 07/06/18.
//  Copyright © 2018 Mauricio Cesar Maniglia Junior. All rights reserved.
//

import UIKit

final class TweetListBuilder: TweetListBuilderProtocol {
    
    static func assembleModule(forTwitterUser twitterUser: String) -> UIViewController {
        let storyboard = UIStoryboard(name: "TweetListStoryboard", bundle: nil)
        let view = storyboard.instantiateViewController(withIdentifier: "TweetListViewController") as! TweetListViewController
        let router = TweetListRouter()
        let presenter = TweetListPresenter()
        let interactor = TweetListInteractor(output: presenter)
        
        view.presenter = presenter
        presenter.view = view
        presenter.router = router
        presenter.twitterUser = twitterUser
        presenter.interactor = interactor
        
        return view
    }
}
