//
//  TweetListCell.swift
//  IfoodMobileTest
//
//  Created by André Vieira on 10/06/19.
//  Copyright © 2019 André Vieira. All rights reserved.
//

import UIKit

final class TweetListCell: UITableViewCell {
    lazy var imgProfile: UIImageView = {
        let img = UIImageView(frame: .zero)
        img.translatesAutoresizingMaskIntoConstraints = false
        img.layer.cornerRadius = 25
        img.clipsToBounds = true
        return img
    }()
    
    lazy var lbTweet: UILabel = {
        let label = UILabel(frame: .zero)
        label.translatesAutoresizingMaskIntoConstraints = false
        label.textColor = .black
        label.font = UIFont.systemFont(ofSize: 12)
        label.textAlignment = .left
        label.text = "djkhfakjshdkjsadfhaksjdfhksjdfhksjdfh"
        label.numberOfLines = 0
        return label
    }()
    
    lazy var lbDate: UILabel = {
        let label = UILabel(frame: .zero)
        label.translatesAutoresizingMaskIntoConstraints = false
        label.textColor = .black
        label.font = UIFont.boldSystemFont(ofSize: 10)
        label.textAlignment = .left
        label.text = "23/03/2344 09:30"
        return label
    }()
    
    func configuretion(tweet: TweetModel) {
        setupViewConfiguration()
        lbTweet.text = tweet.text
        lbDate.text = tweet.createdAt
        imgProfile.loadImage(url: tweet.user?.profileImageURL)
    }
}

extension TweetListCell: ViewConfiguration {
    func buildViewHierarchy() {
        self.contentView.addSubview(imgProfile)
        self.contentView.addSubview(lbTweet)
        self.contentView.addSubview(lbDate)
    }
    
    func setupConstraints() {
        NSLayoutConstraint.activate([
            imgProfile.widthAnchor.constraint(equalToConstant: 50),
            imgProfile.heightAnchor.constraint(equalToConstant: 50),
            imgProfile.topAnchor.constraint(equalTo: self.contentView.topAnchor, constant: 15),
            imgProfile.leadingAnchor.constraint(equalTo: self.contentView.leadingAnchor, constant: 16),
            imgProfile.bottomAnchor.constraint(equalTo: self.contentView.bottomAnchor, constant: -15)
        ])
        
        NSLayoutConstraint.activate([
            lbTweet.topAnchor.constraint(equalTo: self.contentView.topAnchor, constant: 10),
            lbTweet.leadingAnchor.constraint(equalTo: imgProfile.trailingAnchor, constant: 10),
            lbTweet.trailingAnchor.constraint(equalTo: self.contentView.trailingAnchor, constant: -16),
            lbTweet.bottomAnchor.constraint(equalTo: lbDate.topAnchor, constant: -5)
        ])
        
        NSLayoutConstraint.activate([
            lbDate.heightAnchor.constraint(equalToConstant: 15),
            lbDate.bottomAnchor.constraint(equalTo: self.contentView.bottomAnchor, constant: -10),
            lbDate.leadingAnchor.constraint(equalTo: imgProfile.trailingAnchor, constant: 10),
            lbDate.trailingAnchor.constraint(equalTo: self.contentView.trailingAnchor, constant: -16)
        ])
    }
}
