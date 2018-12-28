//
//  TweetCell.swift
//  TwitterSentiment
//
//  Created by Jean Vinge on 26/12/18.
//  Copyright © 2018 Jean Vinge. All rights reserved.
//

import UIKit
import Kingfisher
import Domain
import Utility

class TweetCell: TableViewCell {
    
    // MARK: Var
    
    lazy var nameContainerView = View(with: Customization().backgroundColor(.clear))
    lazy var photoImageView = CircleImageView()
    lazy var nameLabel = Label(with: Customization().font(.boldSystemFont(ofSize: 14)).titleColor(.black))
    lazy var userNameLabel = Label(with: Customization().titleColor(UIColor.black.withAlphaComponent(0.7)).font(.systemFont(ofSize: 10)))
    lazy var messageLabel = Label(with: Customization().numberOfLines(0).font(.systemFont(ofSize: 12)).titleColor(UIColor.black.withAlphaComponent(0.5)))
    lazy var dateLabel = Label(with: Customization().titleColor(.black).font(.italicSystemFont(ofSize: 10)))
    lazy var separatorView = View(with: Customization().backgroundColor(UIColor.black.withAlphaComponent(0.3)))
    
    // MARK: Init
    
    override func initSubviews() {
        self.addSubview(contentView)
        contentView.addSubview(nameContainerView)
        contentView.addSubview(photoImageView)
        nameContainerView.addSubview(nameLabel)
        nameContainerView.addSubview(userNameLabel)
        contentView.addSubview(messageLabel)
        contentView.addSubview(dateLabel)
        contentView.addSubview(separatorView)
    }
    
    override func initConstraints() {
        self.photoImageView.snp.makeConstraints { (make) in
            make.width.height.equalTo(40)
            make.left.equalTo(20)
            make.centerY.equalTo(messageLabel.snp.centerY)
        }
        self.nameContainerView.snp.makeConstraints { (make) in
            make.top.equalToSuperview().inset(5)
            make.left.equalTo(photoImageView.snp.right).offset(10)
            make.right.greaterThanOrEqualToSuperview().inset(30)
        }
        self.nameLabel.snp.makeConstraints { (make) in
            make.top.left.bottom.equalToSuperview()
        }
        self.userNameLabel.snp.makeConstraints { (make) in
            make.left.equalTo(nameLabel.snp.right).offset(5)
            make.top.bottom.equalToSuperview()
        }
        self.messageLabel.snp.makeConstraints { (make) in
            make.top.equalTo(nameLabel.snp.top).offset(20)
            make.left.equalTo(photoImageView.snp.right).offset(10)
            make.right.equalToSuperview().inset(40)
        }
        self.dateLabel.snp.makeConstraints { (make) in
            make.top.equalTo(messageLabel.snp.bottom).offset(5)
            make.left.equalTo(messageLabel.snp.left)
            make.bottom.equalToSuperview().inset(10)
        }
        self.separatorView.snp.makeConstraints { (make) in
            make.height.equalTo(0.5)
            make.left.equalTo(photoImageView.snp.left)
            make.right.bottom.equalToSuperview()
        }
    }
    
    override func configure(at indexPath: IndexPath?, with object: Any?) {
        guard let tweet = object as? Tweet else { return }
        self.photoImageView.kf.setImage(with: tweet.user.profileImageURL)
        self.nameLabel.text = tweet.user.name
        self.userNameLabel.text = tweet.user.decoratedUserName
        self.messageLabel.text = tweet.text
        self.dateLabel.text = DateFormatter.ddMMyyyy(date: tweet.createdAt)
    }
}
