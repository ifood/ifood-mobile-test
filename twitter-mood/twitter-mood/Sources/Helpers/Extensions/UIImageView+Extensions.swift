//
//  UIImageView+Extensions.swift
//  twitter-mood
//
//  Created by Dennis Merli on 7/10/19.
//  Copyright © 2019 Dennis Merli. All rights reserved.
//

import UIKit
import SDWebImage

extension UIImageView {
    
    func loadImageFromURL(_ url: URL?, placeholder: UIImage? = nil, showActivityIndicator: Bool = false) {
        if let url = url {
            if showActivityIndicator {
                self.sd_imageIndicator = SDWebImageActivityIndicator.white
            }
            self.sd_setImage(with: url, placeholderImage: placeholder)
        } else {
            image = placeholder
        }
    }
    
    func loadImageFromURL(_ url: URL?, placeholder: UIImage? = nil, showActivityIndicator: Bool = false, completion: @escaping(_ image: UIImage?) -> Void) {
        guard let url = url else { return }
        
        if showActivityIndicator {
            self.sd_imageIndicator = SDWebImageActivityIndicator.white
        }
        self.sd_setImage(with: url, placeholderImage: placeholder)
    }
    
    func loadImage(urlString: String?, placeholder: UIImage? = nil, showActivityIndicator: Bool = false) {
        guard let urlString = urlString else { return }
        loadImageFromURL(URL(string: urlString), placeholder: placeholder, showActivityIndicator: showActivityIndicator)
    }
    
    func cancelLoad() {
        self.sd_cancelCurrentImageLoad()
    }
}
