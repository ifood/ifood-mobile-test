//
//  TweetListHeaderView.swift
//  TwitterAnalyzer
//
//  Created by Bruno Vieira on 19/06/19.
//  Copyright © 2019 Bruno Vieira. All rights reserved.
//

import UIKit

class TweetListHeaderView: UITableViewHeaderFooterView, CodedViewProtocol {
    
    static let identifier = "TweetListHeaderView"

    private lazy var profileImageView: UIImageView = {
        let imageView = UIImageView()
        imageView.translatesAutoresizingMaskIntoConstraints = false
        imageView.layer.cornerRadius = 25
        imageView.layer.masksToBounds = true
        return imageView
    }()
    
    private lazy var nameLabel: UILabel = {
        let label = UILabel()
        label.translatesAutoresizingMaskIntoConstraints = false
        label.numberOfLines = 0
        label.font = UIFont.boldSystemFont(ofSize: 15)
        return label
    }()
    
    private lazy var userLabel: UILabel = {
        let label = UILabel()
        label.translatesAutoresizingMaskIntoConstraints = false
        label.numberOfLines = 1
        label.font = UIFont.italicSystemFont(ofSize: 12)
        return label
    }()
    
    private lazy var informationContainerView: UIView = {
        let view = UIView()
        view.translatesAutoresizingMaskIntoConstraints = false
        return view
    }()
    
    override init(reuseIdentifier: String?) {
        super.init(reuseIdentifier: reuseIdentifier)
        configureView()
    }
    
    required init?(coder aDecoder: NSCoder) {
        super.init(coder: aDecoder)
        configureView()
    }
    
    func configureView() {
        createSubviews()
        setupConstraints()
        backgroundColor = .white
    }
    
    func createSubviews() {
        addSubview(profileImageView)
        addSubview(informationContainerView)
        informationContainerView.addSubview(nameLabel)
        informationContainerView.addSubview(userLabel)
    }
    
    func setupConstraints() {
        NSLayoutConstraint.activate([profileImageView.leadingAnchor.constraint(equalTo: leadingAnchor, constant: 20),
                                     profileImageView.centerYAnchor.constraint(equalTo: centerYAnchor),
                                     profileImageView.heightAnchor.constraint(equalToConstant: 50),
                                     profileImageView.widthAnchor.constraint(equalTo: profileImageView.heightAnchor)])
        
        NSLayoutConstraint.activate([informationContainerView.heightAnchor.constraint(greaterThanOrEqualToConstant: 50),
                                     informationContainerView.leadingAnchor.constraint(equalTo: profileImageView.trailingAnchor, constant: 20),
                                     informationContainerView.trailingAnchor.constraint(equalTo: trailingAnchor, constant: -20),
                                     informationContainerView.topAnchor.constraint(equalTo: topAnchor, constant: 20),
                                     informationContainerView.bottomAnchor.constraint(equalTo: bottomAnchor, constant: -20)])
        
        NSLayoutConstraint.activate([nameLabel.leadingAnchor.constraint(equalTo: informationContainerView.leadingAnchor),
                                     nameLabel.trailingAnchor.constraint(equalTo: informationContainerView.trailingAnchor),
                                     nameLabel.topAnchor.constraint(equalTo: informationContainerView.topAnchor)])
        
        NSLayoutConstraint.activate([userLabel.leadingAnchor.constraint(equalTo: informationContainerView.leadingAnchor),
                                     userLabel.trailingAnchor.constraint(equalTo: informationContainerView.trailingAnchor),
                                     userLabel.bottomAnchor.constraint(equalTo: informationContainerView.bottomAnchor),
                                     userLabel.topAnchor.constraint(equalTo: nameLabel.bottomAnchor, constant: 10)])
        
        nameLabel.setContentHuggingPriority(UILayoutPriority.defaultLow, for: .vertical)
        userLabel.setContentHuggingPriority(UILayoutPriority.defaultHigh, for: .vertical)
    }
    
    func setHeaderInformation(name: String, user: String, profileImage: UIImage?) {
        nameLabel.text = name
        userLabel.text = user
        profileImageView.image = profileImage       
    }
}
