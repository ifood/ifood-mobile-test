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
        mainView.twitterUserTextField.addTarget(self, action: #selector(textFieldsIsNotEmpty), for: .editingChanged)
    }
    
    @IBAction func didClickListTweets(_ sender: Any) {
        presenter.showTweetsFor(user: mainView.twitterUserTextField.text!)
    }
    
    @objc fileprivate func textFieldsIsNotEmpty(sender: UITextField) {
        sender.text = sender.text?.trimmingCharacters(in: .whitespaces)
        
        guard let name = mainView.twitterUserTextField.text, !name.isEmpty else {
            mainView.listTweetsButton.isEnabled = false
            return
        }
        
        mainView.listTweetsButton.isEnabled = true
    }
    
}
