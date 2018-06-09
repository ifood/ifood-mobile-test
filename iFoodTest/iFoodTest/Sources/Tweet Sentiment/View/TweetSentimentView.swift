//
//  TweetSentimentView.swift
//  iFoodTest
//
//  Created by Mauricio Cesar Maniglia Junior on 09/06/18.
//  Copyright © 2018 Mauricio Cesar Maniglia Junior. All rights reserved.
//

import UIKit

final class TweetSentimentView: UIView {
    
    @IBOutlet weak var containerMessageView: UIView!
    @IBOutlet weak var emojiLabel: UILabel!
    @IBOutlet weak var messageLabel: UILabel!
    @IBOutlet weak var tweetTextLabel: UILabel!
    @IBOutlet weak var activityIndicator: UIActivityIndicatorView!
    @IBOutlet weak var closeButton: UIButton!
    
    override func awakeFromNib() {
        super.awakeFromNib()
        setupView()
    }
    
    fileprivate func setupView() {
        containerMessageView.layer.cornerRadius = 20
    }
}
