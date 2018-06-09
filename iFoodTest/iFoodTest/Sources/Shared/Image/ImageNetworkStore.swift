//
//  ImageNetworkStore.swift
//  iFoodTest
//
//  Created by Mauricio Cesar Maniglia Junior on 09/06/18.
//  Copyright © 2018 Mauricio Cesar Maniglia Junior. All rights reserved.
//

import UIKit

class ImageNetworkStore: ImageStoreProtocol {
    
    func loadImage(url: URL, completion: @escaping (UIImage?, Error?) -> ()) {
        
        let request = URLRequest(url: url)
        
        NetworkClient.sharedInstance.sendRequest(request: request) { data, response, error in
            
            if let imageData = data, let image = UIImage(data: imageData) {
                
                if let responseURL = response?.url, responseURL == url {
                    completion(image, nil)
                }
            } else {                
                completion(nil, nil)
            }
        }
    }
}
