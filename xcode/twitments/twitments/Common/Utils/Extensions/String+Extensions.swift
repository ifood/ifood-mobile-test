//
//  String+Extensions.swift
//  twitments
//
//  Created by Ezequiel on 08/03/18.
//  Copyright © 2018 Ezequiel. All rights reserved.
//

import Foundation

extension String {
    
    var urlHostAllowed: String {
        guard let _string = self.addingPercentEncoding(withAllowedCharacters: .urlHostAllowed) else {
            return self
        }
        return _string
    }
}
