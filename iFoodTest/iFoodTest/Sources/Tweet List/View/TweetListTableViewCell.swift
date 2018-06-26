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
    
    override func awakeFromNib() {
        super.awakeFromNib()
        setupViews()
    }
    
    func setup(_ tweet: Tweet) {
        userNameLabel.text = tweet.user.name
        tweetLabel.text = tweet.text
        userThumbImageView.setImageURL(url: tweet.user.profileImageUrl, placeholder: UIImage(named: "placeholder"))
    }
    
    fileprivate func setupViews() {
        self.userThumbImageView.layer.cornerRadius = self.userThumbImageView.frame.size.width / 2;
        self.userThumbImageView.clipsToBounds = true;
    
        selectionStyle = .none
    }
}
