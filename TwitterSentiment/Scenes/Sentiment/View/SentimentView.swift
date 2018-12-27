//
//  SentimentView.swift
//  TwitterSentiment
//
//  Created by Jean Vinge on 26/12/18.
//  Copyright © 2018 Jean Vinge. All rights reserved.
//

import UIKit
import Utility
import Domain
import Resources
import RxCocoa
import RxSwift

class SentimentView: View {
    
    // MARK: Var

    lazy var indicator = UIActivityIndicatorView(style: .gray)
    lazy var container = View(with: Customization().backgroundColor(.clear))
    lazy var background = UIVisualEffectView(backgroundColor: .white, alpha: 0.9)
    lazy var emojiLabel = Label(with: Customization().font(.systemFont(ofSize: 70)))
    lazy var titleLabel = Label(with: Customization().titleColor(.black).font(.boldSystemFont(ofSize: 14)).numberOfLines(0).alignment(.center))
    lazy var closeButton = Button(with: Customization().image(Asset.iconCloseX.image))
    
    // MARK: Init
    
    func configure(with output: SentimentOutput) {
        self.animate(output)
    }
    
    override func initSubviews() {
        self.addSubview(container)
        container.addSubview(background)
        container.addSubview(emojiLabel)
        container.addSubview(titleLabel)
        self.addSubview(closeButton)
        self.addSubview(indicator)
        self.indicator.hidesWhenStopped = true
    }
    
    override func initConstraints() {
        self.indicator.snp.makeConstraints { (make) in
            make.center.equalToSuperview()
        }
        self.container.snp.makeConstraints { (make) in
            make.top.left.right.bottom.equalToSuperview()
        }
        self.closeButton.snp.makeConstraints { (make) in
            make.top.left.equalToSuperview().inset(20)
            make.height.width.equalTo(50)
        }
        self.background.snp.makeConstraints { (make) in
            make.top.left.right.bottom.equalToSuperview()
        }
        self.emojiLabel.snp.makeConstraints { (make) in
            make.center.equalToSuperview()
        }
        self.titleLabel.snp.makeConstraints { (make) in
            make.left.right.equalToSuperview().inset(40)
            make.top.equalTo(emojiLabel.snp.bottom).offset(10)
        }
    }
    
    // MARK: Animation
    
    func animate(_ output: SentimentOutput) {
        UIView.animate(withDuration: 0.2) { [weak self] in
            guard let self = self else { return }
            self.background.backgroundColor = output.color
            self.emojiLabel.text = output.emoji
            self.titleLabel.text = output.text
        }
    }
}
