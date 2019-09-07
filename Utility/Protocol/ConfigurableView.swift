//
//  ConfigurableView.swift
//  TwitterSentiment
//
//  Created by Jean Vinge on 15/06/2018.
//  Copyright © 2018 Atlas Politico. All rights reserved.
//

import UIKit

public protocol ConfigurableView {
    func initLayout()
    func initSubviews()
    func initConstraints()
}

extension ConfigurableView where Self: UIViewController {
    public func initLayout() {
        self.initSubviews()
        self.initConstraints()
    }
}

extension ConfigurableView where Self: UIView {
    public func initLayout() {
        self.initSubviews()
        self.initConstraints()
    }
}
