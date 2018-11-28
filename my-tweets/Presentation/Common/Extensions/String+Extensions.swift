//
//  String+Extensions.swift
//  my-tweets
//
//  Created by Gabriel Catice on 28/11/18.
//  Copyright © 2018 Gabriel Catice. All rights reserved.
//

import Foundation

extension String {
    var alphanumeric: String {
        return self.components(separatedBy: CharacterSet.alphanumerics.inverted).joined().lowercased()
    }
    
    var formattedDate: String {
        return String(self.prefix(10))
    }
}
