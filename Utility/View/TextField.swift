//
//  TextField.swift
//  TwitterSentiment
//
//  Created by Jean Vinge on 26/12/18.
//  Copyright © 2018 Jean Vinge. All rights reserved.
//

import UIKit

open class TextField: UITextField, ConfigurableView {
    
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
        self.placeholder = customization.placeholder
        self.textAlignment = customization.alignment
        self.adjustsFontSizeToFitWidth = customization.adjustsFontSizeToFitWidth
        self.autocorrectionType = customization.autocorrectionType
        self.keyboardType = customization.keyboardType
        self.returnKeyType = customization.returnKeyType
        self.autocapitalizationType = customization.autocapitalizationType
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
