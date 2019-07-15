//
//  PlistLoader.swift
//  twitter-mood
//
//  Created by Dennis Merli on 7/7/19.
//  Copyright © 2019 Dennis Merli. All rights reserved.
//

import Foundation

final class PlistLoader<T: Codable> {
    
    private var fileName: String = ""
    
    init(fileName: String) {
        self.fileName = fileName
    }
    
    func loadFromPlist() throws -> T {
        guard let url = Bundle.main.url(forResource: fileName, withExtension: "plist") else {
            throw(CustomError.nilParameter(parameter: "Could not load plist file"))
        }
        
        do {
            let data = try Data(contentsOf: url)
            let plistDecoder = PropertyListDecoder()
            let decodedVars = try plistDecoder.decode(T.self, from: data)
            return decodedVars
        } catch {
            throw(error)
        }
    }
}

