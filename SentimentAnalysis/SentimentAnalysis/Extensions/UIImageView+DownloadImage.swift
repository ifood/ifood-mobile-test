//
//  UIImageView+DownloadImage.swift
//  SentimentAnalysis
//
//  Created by Thales Frigo on 28/11/18.
//  Copyright © 2018 Thales Frigo. All rights reserved.
//

import UIKit
import Kingfisher

extension UIImageView {
    
    func download(_ url: URL, placeHolderImage: UIImage? = nil){
        self.kf.setImage(with: url, placeholder: placeHolderImage)
    }
    
    func cancel() {
        self.kf.cancelDownloadTask()
    }
}
