//
//  EmptyViewController.swift
//  SentimentAnalysis
//
//  Created by Thales Frigo on 02/12/18.
//  Copyright © 2018 Thales Frigo. All rights reserved.
//

import UIKit

class EmptyView: UIView {
    
    let container: UIStackView = {
        let stackView = UIStackView()
        stackView.translatesAutoresizingMaskIntoConstraints = false
        stackView.axis = .vertical
        stackView.alignment = .center
        stackView.distribution = .fill
        stackView.spacing = 20
        return stackView
    }()
    
    let image: UIImageView = {
        let imageView = UIImageView(image: UIImage(named: "search"))
        imageView.translatesAutoresizingMaskIntoConstraints = false
        return imageView
    }()
    
    let title: UILabel = {
        let label = UILabel()
        label.translatesAutoresizingMaskIntoConstraints = false
        label.text = Localized(key: "EMPTY_TITLE")
        label.font = UIFont(name: "Avenir-Heavy", size: 20)
        label.textAlignment = .center
        label.numberOfLines = 0
        return label
    }()
    
    let subtitle: UILabel = {
        let label = UILabel()
        label.translatesAutoresizingMaskIntoConstraints = false
        label.text = Localized(key: "EMPTY_MESSAGE")
        label.font = UIFont(name: "Avenir-Book", size: 16)
        label.textAlignment = .center
        label.numberOfLines = 0
        return label
    }()
    
    let ctaButton: ShadowBorderButtonView = {
        let buttonView = ShadowBorderButtonView()
        buttonView.translatesAutoresizingMaskIntoConstraints = false
        buttonView.button.setTitle(Localized(key: "TAP_TO_START"), for: .normal)
        buttonView.button.setBackgroundColor(.primary, for: .normal)
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
            .widthAnchor(equalTo: widthAnchor, constant: -16)
        
        image
            .widthAnchor(equalTo: 120)
            .heightAnchor(equalTo: 120)
        
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
    
    func reset() {
        contentView.image.image = UIImage(named: "search")
        contentView.title.text = Localized(key: "EMPTY_TITLE")
        contentView.subtitle.text = Localized(key: "EMPTY_MESSAGE")
        contentView.ctaButton.button.setTitle(Localized(key: "TAP_TO_START"), for: .normal)
    }
    
    func render( _ error: SearchRepositoryError) {
        contentView.image.image = UIImage(named: error.imageName)
        contentView.title.text = error.failureReason
        contentView.subtitle.text = error.errorDescription
        contentView.ctaButton.button.setTitle(error.recoverySuggestion, for: .normal)
    }
}
