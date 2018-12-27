//
//  Button.swift
//  TwitterSentiment
//
//  Created by Jean Vinge on 23/11/18.
//  Copyright © 2018 Jean Vinge. All rights reserved.
//

import UIKit

public class Button: UIButton, ConfigurableView {

    // MARK: Init
    
    public init() {
        super.init(frame: .zero)
        self.initLayout()
    }
    
    public init(with customization: Customization) {
        super.init(frame: .zero)
        self.setTitle(customization.text, for: .normal)
        self.setTitleColor(customization.titleColor, for: .normal)
        self.setImage(customization.image, for: .normal)
        self.titleLabel?.font = customization.font
        self.backgroundColor = customization.backgroundColor
        self.titleLabel?.textAlignment = customization.alignment
        self.titleLabel?.numberOfLines = customization.numberOfLines
        self.titleLabel?.adjustsFontSizeToFitWidth = customization.adjustsFontSizeToFitWidth
        customization.imageControls.forEach { control in
            self.setImage(control.image, for: control.state)
        }
        self.initLayout()
    }
    
    required public init?(coder aDecoder: NSCoder) {
        super.init(coder: aDecoder)
    }
    
    open func initSubviews() {
    }
    
    open func initConstraints() {
        
    }

}
