//
//  ErrorView.swift
//  tecnonutri
//
//  Created by Thales Frigo on 29/05/18.
//  Copyright © 2018 Grupo Minha Vida. All rights reserved.
//

import UIKit

class ErrorView: UIView {
    
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
        let imageView = UIImageView(image: UIImage(named: "sad"))
        imageView.translatesAutoresizingMaskIntoConstraints = false
        imageView.contentMode = .scaleAspectFit
        return imageView
    }()
    
    let title: UILabel = {
        let label = UILabel()
        label.translatesAutoresizingMaskIntoConstraints = false
        label.text = Localized(key: "ERROR_TITLE")
        label.font = UIFont(name: "Avenir-Heavy", size: 20)
        label.textAlignment = .center
        label.numberOfLines = 0
        return label
    }()
    
    let subtitle: UILabel = {
        let label = UILabel()
        label.translatesAutoresizingMaskIntoConstraints = false
        label.text = Localized(key: "ERROR_MESSAGE")
        label.font = UIFont(name: "Avenir-Book", size: 16)
        label.textAlignment = .center
        label.numberOfLines = 0
        return label
    }()
    
    let reloadButton: ShadowBorderButtonView = {
        let buttonView = ShadowBorderButtonView()
        buttonView.translatesAutoresizingMaskIntoConstraints = false
        buttonView.button.setTitle(Localized(key: "ERROR_CTA"), for: .normal)
        buttonView.button.setBackgroundColor(.primary, for: .normal)
        buttonView.button.titleLabel?.font = UIFont(name: "Avenir-Heavy", size: 12)
        buttonView.button.setTitleColor(.white, for: .normal)
        return buttonView
    }()
    
    let loader: UIActivityIndicatorView = {
        let loader = UIActivityIndicatorView(style: .white)
        loader.translatesAutoresizingMaskIntoConstraints = false
        loader.hidesWhenStopped = true
        loader.isHidden = true
        return loader
    }()
    
    override init(frame: CGRect = .zero) {
        super.init(frame: frame)
        setupViewConfiguration()
    }
    
    required init?(coder aDecoder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }
}

extension ErrorView {
    
    func startAnimating(){
        //        button.setTitle(nil, for: .normal)
        reloadButton.shadow.isHidden = true
        loader.isHidden = false
        loader.startAnimating()
    }
    
    func resetButtonState(){
        reloadButton.shadow.isHidden = false
        loader.isHidden = true
        loader.stopAnimating()
    }
}

extension ErrorView: ViewConfiguration {
    
    func buildViewHierarchy() {
        addSubview(container)
        container.addArrangedSubview(image)
        container.addArrangedSubview(title)
        container.addArrangedSubview(subtitle)
        container.addArrangedSubview(reloadButton)
        reloadButton.addSubview(loader)
    }
    
    func setupConstraints() {
        container
            .centerXAnchor(equalTo: centerXAnchor)
            .centerYAnchor(equalTo: centerYAnchor)
            .widthAnchor(equalTo: widthAnchor, constant: -16)
        
        image
            .widthAnchor(equalTo: 120)
            .heightAnchor(equalTo: 120)
        
        reloadButton
            .widthAnchor(equalTo: 145)
            .heightAnchor(equalTo: 37)
        
        loader
            .centerYAnchor(equalTo: reloadButton.centerYAnchor)
            .centerXAnchor(equalTo: reloadButton.centerXAnchor)
    }
    
    func configureViews() {

    }
}

class ErrorViewController: ViewConfigurationController<ErrorView> {
    
    var reloadHandler: (ErrorViewController?) -> Void = { _ in } {
        didSet {
            contentView.resetButtonState()
        }
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()
        contentView.backgroundColor = .white
        
        contentView.reloadButton.handler = { [weak self] in
            self?.contentView.startAnimating()
            self?.reloadHandler(self)
        }
    }
    
    func render(_ error: Error) {
        contentView.subtitle.text = error.localizedDescription
    }
}
