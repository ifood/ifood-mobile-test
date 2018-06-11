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
    @IBOutlet weak var textLabel: UILabel!
    @IBOutlet weak var activityIndicator: UIActivityIndicatorView!
    @IBOutlet weak var closeButton: UIButton!
    @IBOutlet weak var containerMessageViewTopConstraint: NSLayoutConstraint!
    
    override func awakeFromNib() {
        super.awakeFromNib()
        setupView()
    }
    
    func setupTweetSentiment(_ tweetSentiment: TweetSentiment) {
        emojiLabel.text = tweetSentiment.emoji
        textLabel.text = tweetSentiment.tweet
        containerMessageView.backgroundColor = tweetSentiment.color
        showContainerMessage()
    }
    
    func showError(_ error: String) {
        textLabel.text = error
        showContainerMessage()
    }
    
    fileprivate func setupView() {
        containerMessageView.layer.cornerRadius = 20
        containerMessageViewTopConstraint.constant = -200
        activityIndicator.startAnimating()
    }
    
    fileprivate func showContainerMessage() {
        self.activityIndicator.stopAnimating()
        self.activityIndicator.isHidden = true
        
        UIView.animate(withDuration: 1,
                       delay: 0,
                       usingSpringWithDamping: 0.7,
                       initialSpringVelocity: 0.5,
                       options: .curveLinear,
                       animations: {
                        self.containerMessageViewTopConstraint.constant = 217
                        self.layoutIfNeeded()
        })
    }
}
