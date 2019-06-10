//
//  UIImage+load.swift
//  IfoodMobileTest
//
//  Created by André Vieira on 10/06/19.
//  Copyright © 2019 André Vieira. All rights reserved.
//

import Kingfisher
import UIKit

extension UIImageView {
    
    func loadImage(url: String = "", placeholder: String) {
        if let imageUrl = URL(string: url) {
            let resource = ImageResource(downloadURL: imageUrl, cacheKey: url)
            self.kf.setImage(with: resource,
                             placeholder: UIImage(named: placeholder),
                             options: [.transition(.fade(0.3))],
                             progressBlock: nil,
                             completionHandler: nil)
        } else {
            self.image = UIImage(named: placeholder)
        }
    }
    
    func loadImage(url: URL?, placeholder: String) {
        if let imageUrl = url {
            let resource = ImageResource(downloadURL: imageUrl, cacheKey: url?.absoluteString)
            self.kf.setImage(with: resource,
                             placeholder: UIImage(named: placeholder),
                             options: [.transition(.fade(0.3))],
                             progressBlock: nil,
                             completionHandler: nil)
        } else {
            self.image = UIImage(named: placeholder)
        }
    }
    
    func loadImage(url: String = "") {
        guard let imageUrl = URL(string: url) else { return }
        let resource = ImageResource(downloadURL: imageUrl, cacheKey: url)
        self.kf.setImage(with: resource)
    }
}
