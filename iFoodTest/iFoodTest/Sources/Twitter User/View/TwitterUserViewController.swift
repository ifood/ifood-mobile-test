//
//  TwitterUserViewController.swift
//  iFoodTest
//
//  Created by Mauricio Cesar Maniglia Junior on 06/06/18.
//  Copyright © 2018 Mauricio Cesar Maniglia Junior. All rights reserved.
//

import UIKit

final class TwitterUserViewController: UIViewController, TwitterUserViewProtocol {
    
    var presenter: TwitterUserPresenterProtocol!
    var mainView: TwitterUserView {
        guard let mainView = self.view as? TwitterUserView else { fatalError("ControllerWithMainViewFatalErrorMainView") }
        return mainView
    }

    override func viewDidLoad() {
        super.viewDidLoad()        
    }
    
    @IBAction func didClickListTweets(_ sender: Any) {
        presenter.showTweetsFor(user: mainView.twitterUserTextField.text!)
    }
    
}
