//
//  TweetSentimentView.swift
//  TwitterAnalyzer
//
//  Created by Bruno Vieira on 18/06/19.
//  Copyright © 2019 Bruno Vieira. All rights reserved.
//

import UIKit

class TweetSentimentView: CodedView {
    
    lazy var sentimentView: UIView = {
        let view = UIView()
        view.translatesAutoresizingMaskIntoConstraints = false
        view.backgroundColor = .white
        view.layer.cornerRadius = 10
        view.layer.masksToBounds = true
        return view
    }()
    
    lazy var emojiContainerView: UIView = {
        let view = UIView()
        view.translatesAutoresizingMaskIntoConstraints = false
        return view
    }()
    
    lazy var emojiLabel: UILabel = {
        let label = UILabel()
        label.translatesAutoresizingMaskIntoConstraints = false
        label.font = UIFont.systemFont(ofSize: 150)
        return label
    }()
    
    lazy var separatorView: UIView = {
        let view = UIView()
        view.translatesAutoresizingMaskIntoConstraints = false
        view.backgroundColor = .black
        return view
    }()
    
    lazy var okButton: UIButton = {
        let button = UIButton()
        button.translatesAutoresizingMaskIntoConstraints = false
        button.setTitle("OK", for: .normal)
        button.setTitleColor(.black, for: .normal)
        button.backgroundColor = .white
        return button
    }()
    
    override func configureView() {
        super.configureView()
        backgroundColor = UIColor.black.withAlphaComponent(0.5)
    }
    
    override func createSubviews() {
        addSubview(sentimentView)
        
        sentimentView.addSubview(emojiContainerView)
        sentimentView.addSubview(separatorView)
        sentimentView.addSubview(okButton)
        
        emojiContainerView.addSubview(emojiLabel)
    }
    
    override func setupConstraints() {
        NSLayoutConstraint.activate([sentimentView.leadingAnchor.constraint(equalTo: viewAnchor.leadingAnchor, constant: 40),
                                     sentimentView.trailingAnchor.constraint(equalTo: viewAnchor.trailingAnchor, constant: -40),
                                     sentimentView.topAnchor.constraint(equalTo: viewAnchor.topAnchor, constant: 150),
                                     sentimentView.bottomAnchor.constraint(equalTo: viewAnchor.bottomAnchor, constant: -150)])
        
        NSLayoutConstraint.activate([emojiContainerView.leadingAnchor.constraint(equalTo: sentimentView.leadingAnchor),
                                     emojiContainerView.trailingAnchor.constraint(equalTo: sentimentView.trailingAnchor),
                                     emojiContainerView.topAnchor.constraint(equalTo: sentimentView.topAnchor)])
        
        NSLayoutConstraint.activate([separatorView.topAnchor.constraint(equalTo: emojiContainerView.bottomAnchor),
                                     separatorView.leadingAnchor.constraint(equalTo: sentimentView.leadingAnchor),
                                     separatorView.trailingAnchor.constraint(equalTo: sentimentView.trailingAnchor),
                                     separatorView.heightAnchor.constraint(equalToConstant: 1)])
        
        NSLayoutConstraint.activate([okButton.leadingAnchor.constraint(equalTo: sentimentView.leadingAnchor),
                                     okButton.trailingAnchor.constraint(equalTo: sentimentView.trailingAnchor),
                                     okButton.topAnchor.constraint(equalTo: separatorView.bottomAnchor),
                                     okButton.bottomAnchor.constraint(equalTo: sentimentView.bottomAnchor),
                                     okButton.heightAnchor.constraint(equalToConstant: 40)])
        
        NSLayoutConstraint.activate([emojiLabel.centerXAnchor.constraint(equalTo: emojiContainerView.centerXAnchor),
                                     emojiLabel.centerYAnchor.constraint(equalTo: emojiContainerView.centerYAnchor)])
    }
}
