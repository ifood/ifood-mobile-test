//
//  CodedView.swift
//  TwitterAnalyzer
//
//  Created by Bruno Vieira on 18/06/19.
//  Copyright © 2019 Bruno Vieira. All rights reserved.
//

import UIKit

@objc protocol CodedViewProtocol : class {
    func createSubviews()
    func setupConstraints()
    @objc optional func configureView()
}

class CodedView: UIView, CodedViewProtocol {
    lazy var viewAnchor: UILayoutGuide = {
        var anchor: UILayoutGuide
        anchor = self.layoutMarginsGuide
        if #available(iOS 11, *) {
            anchor = self.safeAreaLayoutGuide
        }
        return anchor
    }()
    
    override init(frame: CGRect) {
        super.init(frame: frame)
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
        fatalError("Subclass didn't override createSubviews selector.")
    }
    
    func setupConstraints() {
        fatalError("Subclass didn't override setupConstraints selector.")
    }
}
