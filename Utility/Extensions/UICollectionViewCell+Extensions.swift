//
//  UICollectionViewCell+Extensions.swift
//  TwitterSentiment
//
//  Created by Jean Vinge on 18/06/2018.
//  Copyright © 2018 Atlas Politico. All rights reserved.
//

import UIKit

extension UICollectionViewCell {
    @objc open func configure(at indexPath: IndexPath? = nil, with object: Any? = nil) {
    }
    @objc open override func size(_ object: Any? = nil) -> CGSize {
        self.configure(with: object)
        return calculatedSize()
    }
    @objc open func size(at indexPath: IndexPath, object: Any? = nil) -> CGSize {
        self.configure(at: indexPath, with: object)
        return calculatedSize()
    }
    @objc open override func calculatedSize() -> CGSize {
        self.setNeedsLayout()
        self.layoutIfNeeded()
        return self.contentView.systemLayoutSizeFitting(UIView.layoutFittingCompressedSize)
    }
}
