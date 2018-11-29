//
//  TweetTableViewCell.swift
//  SentimentAnalysis
//
//  Created by Thales Frigo on 28/11/18.
//  Copyright © 2018 Thales Frigo. All rights reserved.
//

import UIKit
import Reusable

class TweetTableViewCell: UITableViewCell, NibReusable {

    @IBOutlet weak var userImageView: UIImageView!
    @IBOutlet weak var userNameLabel: UILabel!
    @IBOutlet weak var screenNameLabel: UILabel!
    @IBOutlet weak var tweetTextView: UILabel!
    @IBOutlet weak var createdAtLabel: UILabel!
    
    override func prepareForReuse() {
        super.prepareForReuse()
        userImageView.cancel()
    }
    
    override func awakeFromNib() {
        super.awakeFromNib()
    }
    
    func render(_ tweet: Tweet) {
        userImageView.download(tweet.user.image)
        userNameLabel.text = tweet.user.name
        screenNameLabel.text = "@\(tweet.user.screenName)"
        tweetTextView.text = tweet.text
        createdAtLabel.text = DateFormatter.twitterShortDateFormat.string(from: tweet.createdAt)
    }
}
