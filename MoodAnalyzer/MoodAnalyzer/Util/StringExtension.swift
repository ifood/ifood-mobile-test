//
//  StringExtension.swift
//  MoodAnalyzer
//
//  Created by Carlos Henrique Salvador on 15/12/18.
//  Copyright © 2018 Carlos Henrique Salvador. All rights reserved.
//

import Foundation
import UIKit

extension String {
    
    func fromBase64() -> String? {
        guard let data = Data(base64Encoded: self) else {
            return nil
        }
        
        return String(data: data, encoding: .utf8)
    }
    
    func toBase64() -> String {
        return Data(self.utf8).base64EncodedString()
    }
    
    func RTF1738encode() -> String {
        var allowed = CharacterSet.alphanumerics
        allowed.insert(charactersIn: "$-_.+!*'(),")
        return self.addingPercentEncoding(withAllowedCharacters: allowed) ?? ""
    }
}
