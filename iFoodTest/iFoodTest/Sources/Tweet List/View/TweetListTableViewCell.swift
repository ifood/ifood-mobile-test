//
//  TweetListTableViewCell.swift
//  iFoodTest
//
//  Created by Mauricio Cesar Maniglia Junior on 07/06/18.
//  Copyright © 2018 Mauricio Cesar Maniglia Junior. All rights reserved.
//

import UIKit

class TweetListTableViewCell: UITableViewCell {
    
    @IBOutlet weak var userThumbImageView: UIImageView!
    @IBOutlet weak var userNameLabel: UILabel!
    @IBOutlet weak var tweetLabel: UILabel!
    
    func setup(_ tweet: String) {
        userNameLabel.text = tweet
        tweetLabel.text = "blablablala"
    }
    
    override func awakeFromNib() {
        super.awakeFromNib()
        // Initialization code
    }
}
