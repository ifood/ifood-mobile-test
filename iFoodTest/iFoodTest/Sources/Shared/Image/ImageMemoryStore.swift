//
//  ImageMemoryStore.swift
//  iFoodTest
//
//  Created by Mauricio Cesar Maniglia Junior on 09/06/18.
//  Copyright © 2018 Mauricio Cesar Maniglia Junior. All rights reserved.
//

import UIKit

class ImageMemoryStore: ImageStoreProtocol {
    
    fileprivate var imageCache = [String: UIImage]()
    
    init() {
        NotificationCenter.default.addObserver(self, selector: #selector(handleMemoryNotification), name: .UIApplicationDidReceiveMemoryWarning, object: nil)
    }
    
    deinit {
        NotificationCenter.default.removeObserver(self, name: .UIApplicationDidReceiveMemoryWarning, object: nil)
    }
    
    func loadImage(url: URL, completion: @escaping (UIImage?, Error?) -> ()) {
        
        let cacheKey = key(url: url)
        let image = imageCache[cacheKey]
        
        completion(image, nil)
    }
    
    func saveImage(image: UIImage?, url: URL) {
        let cacheKey = key(url: url)
        imageCache[cacheKey] = image
    }
    
    func removeAllImages() {
        self.imageCache.removeAll()
    }
    
    private func key(url: URL) -> String {
        return url.absoluteString
    }
    
    @objc func handleMemoryNotification() {        
        self.removeAllImages()
    }
}
