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
    
    lazy var loader: UIActivityIndicatorView = {
        let load = UIActivityIndicatorView(style: .whiteLarge)
        load.translatesAutoresizingMaskIntoConstraints = false
        load.hidesWhenStopped = true
        load.color = UIColor(red: 29/255.0, green: 161/255.0, blue: 242/255.0, alpha: 1.0)
        return load
    }()
    
    convenience init() {
        self.init(frame: .zero)
        setupViewConfiguration()
    }
}

extension TweetAnalyzeView: ViewConfiguration {
    func buildViewHierarchy() {
        self.addSubview(emoji)
        self.addSubview(loader)
    }
    
    func setupConstraints() {
        NSLayoutConstraint.activate([
            emoji.centerXAnchor.constraint(equalTo: self.centerXAnchor),
            emoji.centerYAnchor.constraint(equalTo: self.centerYAnchor)
        ])
        
        NSLayoutConstraint.activate([
            loader.centerXAnchor.constraint(equalTo: self.centerXAnchor),
            loader.centerYAnchor.constraint(equalTo: self.centerYAnchor),
            loader.widthAnchor.constraint(equalToConstant: 40),
            loader.heightAnchor.constraint(equalToConstant: 40)
        ])
    }
}
