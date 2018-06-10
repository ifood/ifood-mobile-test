//
//  ImageManager.swift
//  iFoodTest
//
//  Created by Mauricio Cesar Maniglia Junior on 09/06/18.
//  Copyright © 2018 Mauricio Cesar Maniglia Junior. All rights reserved.
//

import UIKit

class ImageManager {
    
    static let sharedInstance = ImageManager()
    private let memoryStore = ImageMemoryStore()
    private let networkStore = ImageNetworkStore()
    var updateNetworkStatusActivityIndicator: Bool = true
    
    func loadImage(url: URL, completion: @escaping (UIImage?, Error?) -> ()) {
        
        memoryStore.loadImage(url: url) { [weak self] cachedImage, memoryStoreError in
            
            if let strongSelf = self {                
                if let _ = cachedImage {
                    completion(cachedImage, nil)
                } else {
                    strongSelf.setNetworkActivityIndicatorVisible(visible: true)
                    strongSelf.networkStore.loadImage(url: url, completion: { downloadedImage, networkStoreError in
                        
                        strongSelf.setNetworkActivityIndicatorVisible(visible: false)
                        strongSelf.memoryStore.saveImage(image: downloadedImage, url: url)
                        
                        completion(downloadedImage, networkStoreError)
                    })
                }
            }
        }
    }
    
    func clearCache() {
        memoryStore.removeAllImages()
    }
    
    private func setNetworkActivityIndicatorVisible(visible: Bool) {        
        if updateNetworkStatusActivityIndicator {
            UIApplication.shared.isNetworkActivityIndicatorVisible = visible
        }
    }
}
