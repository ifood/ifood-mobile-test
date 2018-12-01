//
//  Utils.swift
//  ifood-devtest
//
//  Created by Rafael Zilião on 01/12/18.
//  Copyright © 2018 Rafael Zilião. All rights reserved.
//

import Foundation

func delay(seconds: Double, completion: @escaping ()-> Void) {
    DispatchQueue.main.asyncAfter(deadline: .now() + seconds, execute: completion)
}
