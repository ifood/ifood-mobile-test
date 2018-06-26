//
//  ImageStoreProtocol.swift
//  iFoodTest
//
//  Created by Mauricio Cesar Maniglia Junior on 09/06/18.
//  Copyright © 2018 Mauricio Cesar Maniglia Junior. All rights reserved.
//

import UIKit

protocol ImageStoreProtocol {
    
    func loadImage(url: URL, completion: @escaping (UIImage?, Error?) -> ())
}
