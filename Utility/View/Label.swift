//
//  Label.swift
// TwitterSentiment
//
//  Created by Jean Vinge on 23/11/18.
//  Copyright © 2018 Jean Vinge. All rights reserved.
//

import UIKit

open class Label: UILabel, ConfigurableView {

    public init() {
        super.init(frame: .zero)
        self.initLayout()
    }

    public init(with customization: Customization) {
        super.init(frame: .zero)
        self.textColor = customization.titleColor
        self.backgroundColor = customization.backgroundColor
        self.font = customization.font
        self.text = customization.text
        self.textAlignment = customization.alignment
        self.numberOfLines = customization.numberOfLines
        self.adjustsFontSizeToFitWidth = customization.adjustsFontSizeToFitWidth
        self.initLayout()
    }

    required public init?(coder aDecoder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }

    open func initSubviews() {
    }

    open func initConstraints() {
    }
}
