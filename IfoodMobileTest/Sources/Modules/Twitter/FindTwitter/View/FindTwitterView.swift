//
//  FindTwitterView.swift
//  IfoodMobileTest
//
//  Created by André Vieira on 09/06/19.
//  Copyright © 2019 André Vieira. All rights reserved.
//

import UIKit

final class FindTwitterView: UIView {
    
    lazy var lbMesage: UILabel = {
        let label = UILabel(frame: .zero)
        label.translatesAutoresizingMaskIntoConstraints = false
        label.textColor = .black
        label.textAlignment = .center
        label.text = L10n.FindUser.twitterMessage
        label.numberOfLines = 0
        return label
    }()
    
    lazy var txtTwitter: UITextField = {
        let textField = UITextField(frame: .zero)
        textField.translatesAutoresizingMaskIntoConstraints = false
        textField.textColor = .black
        textField.tintColor = .black
        textField.borderStyle = .roundedRect
        textField.placeholder = "LewisHamilton"
        return textField
    }()
    
    lazy var btnFind: UIButton = {
        let button = UIButton(frame: .zero)
        button.translatesAutoresizingMaskIntoConstraints = false
        button.tintColor = .black
        button.backgroundColor = UIColor(red: 29/255.0, green: 161/255.0, blue: 242/255.0, alpha: 1.0)
        button.setTitle(L10n.DefaultText.find, for: .normal)
        button.layer.cornerRadius = 22
        return button
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
        self.backgroundColor = .white
        setupViewConfiguration()
    }
}

extension FindTwitterView: ViewConfiguration {
    func buildViewHierarchy() {
        self.addSubview(lbMesage)
        self.addSubview(btnFind)
        self.addSubview(txtTwitter)
        self.addSubview(loader)
    }
    
    func setupConstraints() {
        NSLayoutConstraint.activate([
            lbMesage.topAnchor.constraint(equalTo: self.topAnchor, constant: 150),
            lbMesage.leadingAnchor.constraint(equalTo: self.leadingAnchor, constant: 16),
            lbMesage.trailingAnchor.constraint(equalTo: self.trailingAnchor, constant: -16)
        ])
        
        NSLayoutConstraint.activate([
            txtTwitter.topAnchor.constraint(equalTo: lbMesage.bottomAnchor, constant: 20),
            txtTwitter.leadingAnchor.constraint(equalTo: self.leadingAnchor, constant: 16),
            txtTwitter.trailingAnchor.constraint(equalTo: self.trailingAnchor, constant: -16)
        ])
        
        NSLayoutConstraint.activate([
            btnFind.topAnchor.constraint(equalTo: txtTwitter.bottomAnchor, constant: 20),
            btnFind.leadingAnchor.constraint(equalTo: self.leadingAnchor, constant: 16),
            btnFind.trailingAnchor.constraint(equalTo: self.trailingAnchor, constant: -16),
            btnFind.heightAnchor.constraint(equalToConstant: 44)
        ])
        
        NSLayoutConstraint.activate([
            loader.centerXAnchor.constraint(equalTo: self.centerXAnchor),
            loader.topAnchor.constraint(equalTo: btnFind.bottomAnchor, constant: 10)
        ])
    }
}
