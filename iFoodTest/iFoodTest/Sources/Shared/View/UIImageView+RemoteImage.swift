//
//  UIImageView+RemoteImage.swift
//  iFoodTest
//
//  Created by Mauricio Cesar Maniglia Junior on 09/06/18.
//  Copyright © 2018 Mauricio Cesar Maniglia Junior. All rights reserved.
//

import UIKit

extension UIImageView {
    
    func setImageURL(url: URL?, placeholder: UIImage? = nil) {        
        guard let imageURL = url else {
            image = placeholder
            return
        }
        
        ImageManager.sharedInstance.loadImage(url: imageURL) { [weak self] image, error in
            if let strongSelf = self {
                strongSelf.image = image
            }
        }
    }
}
