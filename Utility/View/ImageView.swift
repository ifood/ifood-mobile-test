//
//  ImageView.swift
//  TwitterSentiment
//
//  Created by Jean Vinge on 22/06/2018.
//  Copyright © 2018 Atlas Politico. All rights reserved.
//

import UIKit

open class ImageView: UIImageView, ConfigurableView {
    
    // MARK: Init
    
    public init(image: UIImage) {
        super.init(image: image)
        self.initLayout()
    }
    
    public init() {
        super.init(frame: .zero)
        self.initLayout()
    }
    
    required public init?(coder aDecoder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }

    open func initSubviews() {
        self.contentMode = .scaleAspectFill
        self.clipsToBounds = true
    }
    
    open func initConstraints() {
    }
}

open class CircleImageView: ImageView {
    
    // MARK: Helpers
    
    override open func layoutSubviews() {
        super.layoutSubviews()
        self.roundCorners(.allCorners, radius: self.bounds.size.width / 2)
    }
}
