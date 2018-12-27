//
//  CollectionViewCell.swift
//  TwitterSentiment
//
//  Created by Jean Vinge on 18/06/2018.
//  Copyright © 2018 Atlas Politico. All rights reserved.
//

import UIKit

open class CollectionViewCell: UICollectionViewCell, ConfigurableView {
    
    // MARK: Init
    
    public override init(frame: CGRect) {
        super.init(frame: frame)
        self.initLayout()
        self.initialization()
    }
    
    required public init?(coder aDecoder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }
    
    private func initialization() {
        self.subviews.forEach { view in
            view.translatesAutoresizingMaskIntoConstraints = false
        }
    }
    
    open func initSubviews() {
    }
    
    open func initConstraints() {
    }
}
