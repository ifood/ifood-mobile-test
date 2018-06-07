//
//  TwitterUserBuilder.swift
//  iFoodTest
//
//  Created by Mauricio Cesar Maniglia Junior on 06/06/18.
//  Copyright © 2018 Mauricio Cesar Maniglia Junior. All rights reserved.
//

import UIKit

final class TwitterUserBuilder: TwitterUserBuilderProtocol {
    
    static func assembleModule() -> UIViewController {
        let storyboard = UIStoryboard(name: "TwitterUserStoryboard", bundle: nil)
        let view = storyboard.instantiateViewController(withIdentifier: "TwitterUserViewController") as! TwitterUserViewController
        let router = TwitterUserRouter()
        let presenter = TwitterUserPresenter()
        let navigation = UINavigationController(rootViewController: view)
        
        view.presenter = presenter
        presenter.router = router
        
        return navigation
    }
}
