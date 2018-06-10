//
//  TwitterUserView.swift
//  iFoodTest
//
//  Created by Mauricio Cesar Maniglia Junior on 07/06/18.
//  Copyright © 2018 Mauricio Cesar Maniglia Junior. All rights reserved.
//

import UIKit

final class TwitterUserView: UIView {
    
    @IBOutlet weak var twitterUserHomeTitle: UILabel!
    @IBOutlet weak var twitterUserTextField: UITextField!
    @IBOutlet weak var listTweetsButton: UIButton!
    
    override func awakeFromNib() {
        super.awakeFromNib()
        setupViews()
    }
    
    fileprivate func setupViews() {
        listTweetsButton.isEnabled = false
        listTweetsButton.setTitle(Localization.TwitterUser.listTweetsButtonTitle, for: .normal)
        twitterUserHomeTitle.text = Localization.TwitterUser.homeTitle
    }
}
