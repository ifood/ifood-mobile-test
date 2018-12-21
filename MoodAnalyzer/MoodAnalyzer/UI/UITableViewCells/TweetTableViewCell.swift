//
//  TweetTableViewCell.swift
//  MoodAnalyzer
//
//  Created by Carlos Henrique Salvador on 19/12/18.
//  Copyright © 2018 Carlos Henrique Salvador. All rights reserved.
//

import UIKit

enum Mood {
    case happy
    case neutral
    case sad
}

class TweetTableViewCell: UITableViewCell {
    
    // MARK: - Variables
    var tweet : Tweet? = nil
    var mood : Mood? = nil
    
    // MARK: - IBOutlets
    @IBOutlet weak var innerView: UIView! {
        didSet {
            self.innerView.layer.borderWidth = 1.0
            self.innerView.layer.cornerRadius = 16.0
        }
    }
    @IBOutlet weak var tweetTextLabel: UILabel! {
        didSet {
            self.tweetTextLabel.textColor = .downriverBlue
            self.tweetTextLabel.lineBreakMode = .byWordWrapping
            self.tweetTextLabel.numberOfLines = 0
            self.tweetTextLabel.font = UIFont.preferredFont(forTextStyle: .headline)
            self.tweetTextLabel.adjustsFontForContentSizeCategory = true
        }
    }
    @IBOutlet weak var dateLabel: UILabel! {
        didSet {
            self.dateLabel.textColor = .bilobaFlowerPink
            self.dateLabel.font = FontUtils.getScaledFont(forFont: "Montserrat-Bold", textStyle: .footnote)
            self.dateLabel.adjustsFontForContentSizeCategory = true
        }
    }
    @IBOutlet weak var moodLabel: UILabel! {
        didSet {
        }
    }
    
    // MARK: - Cell Functions
    override func awakeFromNib() {
        super.awakeFromNib()
        
        self.setUI()
    }
    
    override func setSelected(_ selected: Bool, animated: Bool) {
        super.setSelected(selected, animated: animated)
        
        self.selectionStyle = .none
    }
    
    // MARK: - Setup UI
    func setUI() {
        
        if let tweet = self.tweet {
            
            self.tweetTextLabel.text = tweet.text
            self.dateLabel.text = DateUtils.parseTwitterDate(twitterDate: tweet.date, outputDateFormat: "DD MMM YYYY")
            
            
            if let mood = tweet.mood {
                self.setWithMood(mood: mood)
            } else {
                self.moodLabel.text = ""
                self.innerView.layer.borderColor = UIColor.charcoalGray.cgColor
                CALayer.removeDropShadow(layer: self.innerView.layer)
            }
        }
    }
    
    func setWithMood(mood: Mood) {

        switch mood {
        case .happy:
            self.moodLabel.text = "😄"
            self.innerView.layer.borderColor = UIColor.candleLightYellow.cgColor
            CALayer.setDropShadow(layer: self.innerView.layer, radius: 5.0, opacity: 0.5, color: .candleLightYellow)
        case .neutral:
            self.moodLabel.text = "😐"
            self.innerView.layer.borderColor = UIColor.charcoalGray.cgColor
            CALayer.setDropShadow(layer: self.innerView.layer, radius: 5.0, opacity: 0.5, color: .charcoalGray)
        case .sad:
            self.moodLabel.text = "☹️"
            self.innerView.layer.borderColor = UIColor.persianBlue.cgColor
            CALayer.setDropShadow(layer: self.innerView.layer, radius: 5.0, opacity: 0.5, color: .persianBlue)
        }
    }
    
}
