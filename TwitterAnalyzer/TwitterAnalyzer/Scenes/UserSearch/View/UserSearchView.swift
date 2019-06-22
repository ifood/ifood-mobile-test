//
//  UserSearchView.swift
//  TwitterAnalyzer
//
//  Created by Bruno Vieira on 18/06/19.
//  Copyright © 2019 Bruno Vieira. All rights reserved.
//

import UIKit

class UserSearchView: CodedView {
    
    lazy var searchButton: UIButton = {
        let button = UIButton()
        button.translatesAutoresizingMaskIntoConstraints = false
        button.setTitle("SEARCH", for: .normal)
        button.backgroundColor = UIColor.colorWith(red: 3, green: 163, blue: 232)
        button.layer.borderColor = UIColor.black.cgColor
        button.layer.borderWidth = 1
        button.layer.cornerRadius = 20
        return button
    }()
    
    lazy var descriptionLabel: UILabel = {
        let label = UILabel()
        label.translatesAutoresizingMaskIntoConstraints = false
        label.text = "Enter a user account to search for tweets:"
        return label
    }()
    
    lazy var userTextField: UITextField = {
        let textField = UITextField()
        textField.translatesAutoresizingMaskIntoConstraints = false
        textField.placeholder = "mkbhd"
        textField.borderStyle = .roundedRect
        textField.autocorrectionType = .no
        textField.returnKeyType = .done
        textField.autocapitalizationType = .none
        return textField
    }()
    
    override func configureView() {
        super.configureView()
        backgroundColor = .white
    }
    
    override func createSubviews() {
        addSubview(descriptionLabel)
        addSubview(userTextField)
        addSubview(searchButton)
    }
    
    override func setupConstraints() {
        NSLayoutConstraint.activate([descriptionLabel.leadingAnchor.constraint(equalTo: viewAnchor.leadingAnchor, constant: 20),
                                     descriptionLabel.topAnchor.constraint(equalTo: viewAnchor.topAnchor, constant: 20),
                                     descriptionLabel.trailingAnchor.constraint(equalTo: trailingAnchor, constant: -20)])
        
        NSLayoutConstraint.activate([userTextField.topAnchor.constraint(equalTo: descriptionLabel.bottomAnchor, constant: 20),
                                     userTextField.leadingAnchor.constraint(equalTo: viewAnchor.leadingAnchor, constant: 20),
                                     userTextField.trailingAnchor.constraint(equalTo: viewAnchor.trailingAnchor, constant: -20),
                                     userTextField.heightAnchor.constraint(equalToConstant: 40)])
        
        NSLayoutConstraint.activate([searchButton.topAnchor.constraint(equalTo: userTextField.bottomAnchor, constant: 20),
                                     searchButton.centerXAnchor.constraint(equalTo: viewAnchor.centerXAnchor),
                                     searchButton.widthAnchor.constraint(equalToConstant: 150),
                                     searchButton.heightAnchor.constraint(equalToConstant: 40)])
    }
}
