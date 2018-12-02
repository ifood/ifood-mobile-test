//
//  EmptyView.swift
//  SentimentAnalysis
//
//  Created by Thales Frigo on 02/12/18.
//  Copyright © 2018 Thales Frigo. All rights reserved.
//

import Foundation
//
//  EmptyView.swift
//  tecnonutri
//
//  Created by Thales Frigo on 26/06/2018.
//  Copyright © 2018 Grupo Minha Vida. All rights reserved.
//

import UIKit

class EmptyView: UIView {
    
    let container: UIStackView = {
        let stackView = UIStackView()
        stackView.translatesAutoresizingMaskIntoConstraints = false
        stackView.axis = .vertical
        stackView.alignment = .center
        stackView.distribution = .fill
        stackView.spacing = 16
        return stackView
    }()
    
    let image: UIImageView = {
        let imageView = UIImageView(image: UIImage(named: ""))
        imageView.translatesAutoresizingMaskIntoConstraints = false
        return imageView
    }()
    
    let title: UILabel = {
        let label = UILabel()
        label.translatesAutoresizingMaskIntoConstraints = false
        label.text = "EMPTY_TITLE"
        label.font = UIFont(name: "Avenir-Heavy", size: 20)
        label.textAlignment = .center
        label.numberOfLines = 0
        return label
    }()
    
    let subtitle: UILabel = {
        let label = UILabel()
        label.translatesAutoresizingMaskIntoConstraints = false
        label.text = "EMPTY_MESSAGE"
        label.font = UIFont(name: "Avenir-Book", size: 16)
        label.textAlignment = .center
        label.numberOfLines = 0
        return label
    }()
    
    let ctaButton: ShadowBorderButtonView = {
        let buttonView = ShadowBorderButtonView()
        buttonView.translatesAutoresizingMaskIntoConstraints = false
        buttonView.button.setTitle("TAP_TO_START", for: .normal)
        buttonView.button.setBackgroundColor(.blue, for: .normal)
        buttonView.button.titleLabel?.font = UIFont(name: "Avenir-Heavy", size: 12)
        buttonView.button.setTitleColor(.white, for: .normal)
        return buttonView
    }()
    
    override init(frame: CGRect = .zero) {
        super.init(frame: frame)
        setupViewConfiguration()
    }
    
    required init?(coder aDecoder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }
}

extension EmptyView: ViewConfiguration {
    
    func buildViewHierarchy() {
        addSubview(container)
        container.addArrangedSubview(image)
        container.addArrangedSubview(title)
        container.addArrangedSubview(subtitle)
        container.addArrangedSubview(ctaButton)
    }
    
    func setupConstraints() {
        container
            .centerYAnchor(equalTo: centerYAnchor)
            .centerXAnchor(equalTo: centerXAnchor)
            .widthAnchor(equalTo: widthAnchor)
        
        image
            .widthAnchor(equalTo: 100)
            .heightAnchor(equalTo: 100)
        
        ctaButton
            .widthAnchor(equalTo: 145)
            .heightAnchor(equalTo: 37)
    }
    
    func configureViews() {
        
    }
}

class EmptyViewController: ViewConfigurationController<EmptyView> {
    
    var tapHandler: (() -> Void)?
    
    override func viewDidLoad() {
        super.viewDidLoad()
        contentView.backgroundColor = .white
        contentView.ctaButton.handler = { [weak self] in
            self?.tapHandler?()
        }
    }
}
