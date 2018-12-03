//
//  ShadowBorderButtonView.swift
//  tecnonutri
//
//  Created by Thales Frigo on 29/05/18.
//  Copyright © 2018 Grupo Minha Vida. All rights reserved.
//

import UIKit

class ShadowBorderButtonView: UIView {
    
    let shadow: UIView = {
        let view = UIView()
        view.translatesAutoresizingMaskIntoConstraints = false
        view.backgroundColor = .clear
        return view
    }()
    
    let button: UIButton = {
        let button = UIButton(type: .custom)
        button.translatesAutoresizingMaskIntoConstraints = false
        button.showsTouchWhenHighlighted = true
        return button
    }()
    
    var handler: (() -> Void)?
    
    override init(frame: CGRect = .zero) {
        super.init(frame: frame)
        setupViewConfiguration()
    }
    
    required init?(coder aDecoder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }
    
    override func layoutSubviews() {
        configureViews()
    }
    
    @objc func didTap(_ sender: Any){
        handler?()
    }
}

extension ShadowBorderButtonView: ViewConfiguration {
    
    func buildViewHierarchy() {
        addSubview(shadow)
        shadow.addSubview(button)
    }
    
    func setupConstraints() {
        shadow
            .topAnchor(equalTo: topAnchor)
            .leadingAnchor(equalTo: leadingAnchor)
            .trailingAnchor(equalTo: trailingAnchor)
            .bottomAnchor(equalTo: bottomAnchor)
        
        button
            .topAnchor(equalTo: shadow.topAnchor, constant: 1)
            .leadingAnchor(equalTo: shadow.leadingAnchor, constant: 2)
            .trailingAnchor(equalTo: shadow.trailingAnchor, constant: -2)
            .bottomAnchor(equalTo: shadow.bottomAnchor, constant: -1)
    }
    
    func configureViews() {
        button.layoutIfNeeded()
        shadow.layoutIfNeeded()
        
        button.layer.cornerRadius = button.bounds.height/2
        button.layer.masksToBounds = true
        button.showsTouchWhenHighlighted = true
        
        shadow.layer.cornerRadius = shadow.bounds.height/2
        shadow.layer.masksToBounds = true
        shadow.layer.shadowColor = UIColor.black.cgColor
        shadow.layer.shadowOffset = CGSize(width: 5, height: 5)
        shadow.layer.shadowRadius = 10
        shadow.layer.shadowOpacity = 0.1
        
        button.addTarget(self, action: #selector(didTap(_:)), for: .touchUpInside)
    }
}


