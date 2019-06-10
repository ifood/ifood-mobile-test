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
    func loadImage(url: URL?) {
        guard let imageUrl = url else { return }
        let resource = ImageResource(downloadURL: imageUrl, cacheKey: imageUrl.absoluteString)
        self.kf.setImage(with: resource)
    }
}
