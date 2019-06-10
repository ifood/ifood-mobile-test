//
//  TweetAnalyzeView.swift
//  IfoodMobileTest
//
//  Created by André Vieira on 10/06/19.
//  Copyright © 2019 André Vieira. All rights reserved.
//

import UIKit

final class TweetAnalyzeView: UIView {
    lazy var emoji: UILabel = {
        let label = UILabel(frame: .zero)
        label.translatesAutoresizingMaskIntoConstraints = false
        label.font = UIFont.systemFont(ofSize: 150)
        label.textAlignment = .center
        return label
    }()
    
    convenience init() {
        self.init(frame: .zero)
        setupViewConfiguration()
    }
}

extension TweetAnalyzeView: ViewConfiguration {
    func buildViewHierarchy() {
        self.addSubview(emoji)
    }
    
    func setupConstraints() {
        NSLayoutConstraint.activate([
            emoji.centerXAnchor.constraint(equalTo: self.centerXAnchor),
            emoji.centerYAnchor.constraint(equalTo: self.centerYAnchor)
        ])
    }
}
