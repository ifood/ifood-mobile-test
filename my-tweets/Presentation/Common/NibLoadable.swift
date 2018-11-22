//
//  NibLoadable.swift
//  my-tweets
//
//  Created by Gabriel Catice on 22/11/18.
//  Copyright © 2018 Gabriel Catice. All rights reserved.
//

import UIKit

protocol NibLoadable: class {}

// Set NibLoadable for all UIViewController and UIView
extension UIViewController: NibLoadable {}
extension UIView: NibLoadable {}

// MARK: - Default implementations

extension NibLoadable where Self: UIView {
    static var reuseIdentifier: String {
        return String(describing: self)
    }
    
    static var nib: UINib {
        return UINib(nibName: String(describing: self), bundle: Bundle(for: self))
    }
    
    func loadNibContent() {
        let layoutAttributes: [NSLayoutConstraint.Attribute] = [.top, .leading, .bottom, .trailing]
        for view in Self.nib.instantiate(withOwner: self, options: nil) {
            if let view = view as? UIView {
                view.translatesAutoresizingMaskIntoConstraints = false
                self.addSubview(view)
                layoutAttributes.forEach { attribute in
                    self.addConstraint(NSLayoutConstraint(item: view,
                                                          attribute: attribute,
                                                          relatedBy: .equal,
                                                          toItem: self,
                                                          attribute: attribute,
                                                          multiplier: 1,
                                                          constant: 0.0))
                }
            }
        }
    }
}

extension NibLoadable where Self: UIViewController {
    static func instantiate() -> Self {
        return Self(nibName: String(describing: self), bundle: Bundle(for: self))
    }
}

