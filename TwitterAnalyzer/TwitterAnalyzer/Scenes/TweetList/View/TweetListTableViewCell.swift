//
//  TweetListTableViewCell.swift
//  TwitterAnalyzer
//
//  Created by Bruno Vieira on 19/06/19.
//  Copyright © 2019 Bruno Vieira. All rights reserved.
//

import UIKit

class TweetListTableViewCell: UITableViewCell, CodedViewProtocol {
    
    static let identifier = "TweetListTableViewCell"
    
    private lazy var tweetLabel: UILabel = {
        let label = UILabel()
        label.translatesAutoresizingMaskIntoConstraints = false
        label.numberOfLines = 0
        label.text = "-"
        return label
    }()
    
    private lazy var dateLabel: UILabel = {
        let label = UILabel()
        label.translatesAutoresizingMaskIntoConstraints = false
        label.numberOfLines = 1
        label.font = UIFont.italicSystemFont(ofSize: 10)
        label.textColor = UIColor.darkGray
        label.text = "-"
        return label
    }()
    
    private lazy var detailLabel: UILabel = {
        let label = UILabel()
        label.translatesAutoresizingMaskIntoConstraints = false
        label.numberOfLines = 0
        label.font = UIFont.boldSystemFont(ofSize: 10)
        label.text = "-"
        return label
    }()
    
    override init(style: UITableViewCell.CellStyle, reuseIdentifier: String?) {
        super.init(style: style, reuseIdentifier: reuseIdentifier)
        configureView()
    }
    
    required init?(coder aDecoder: NSCoder) {
        super.init(coder: aDecoder)
        configureView()
    }
    
    func configureView() {
        createSubviews()
        setupConstraints()
    }
    
    func createSubviews() {
        addSubview(dateLabel)
        addSubview(tweetLabel)
        addSubview(detailLabel)
    }
    
    func setupConstraints() {
        NSLayoutConstraint.activate([dateLabel.leadingAnchor.constraint(equalTo: leadingAnchor, constant: 20),
                                     dateLabel.trailingAnchor.constraint(equalTo: trailingAnchor, constant: -20),
                                     dateLabel.topAnchor.constraint(equalTo: topAnchor, constant: 20)])
        
        NSLayoutConstraint.activate([tweetLabel.leadingAnchor.constraint(equalTo: leadingAnchor, constant: 20),
                                     tweetLabel.trailingAnchor.constraint(equalTo: trailingAnchor, constant: -20),
                                     tweetLabel.topAnchor.constraint(equalTo: dateLabel.bottomAnchor, constant: 20)])
        
        NSLayoutConstraint.activate([detailLabel.leadingAnchor.constraint(greaterThanOrEqualTo: leadingAnchor, constant: 20),
                                     detailLabel.trailingAnchor.constraint(equalTo: trailingAnchor, constant: -20),
                                     detailLabel.topAnchor.constraint(equalTo: tweetLabel.bottomAnchor, constant: 20),
                                     detailLabel.bottomAnchor.constraint(equalTo: bottomAnchor, constant: -20)])
        
        tweetLabel.setContentHuggingPriority(.defaultLow, for: .vertical)
        dateLabel.setContentHuggingPriority(.defaultHigh, for: .vertical)
        detailLabel.setContentHuggingPriority(.defaultHigh, for: .vertical)
    }
    
    func setTweetInformation(text: String, date: String, detail: String?) {
        tweetLabel.text = text
        dateLabel.text = date
        detailLabel.text = detail
    }
}
