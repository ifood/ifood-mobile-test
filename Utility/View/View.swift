//
//  View.swift
//  TwitterSentiment
//
//  Created by Jean Vinge on 15/06/2018.
//  Copyright © 2018 Atlas Politico. All rights reserved.
//

import UIKit

open class View: UIView, ConfigurableView {

    // MARK: Init

    public init() {
        super.init(frame: .zero)
        self.initLayout()
    }

    public override init(frame: CGRect) {
        super.init(frame: frame)
        self.initLayout()
    }

    public convenience init(with customization: Customization) {
        self.init()
        self.backgroundColor = customization.backgroundColor
        self.layer.cornerRadius = customization.cornerRadius
        self.layer.masksToBounds = customization.masksToBounds
        self.initLayout()
    }

    public required init?(coder aDecoder: NSCoder) {
        super.init(coder: aDecoder)
        self.initLayout()
    }

    open func initSubviews() {
    }

    open func initConstraints() {
    }
}
