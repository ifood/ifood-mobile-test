//
//  Data+JSON.swift
//  iFoodTest
//
//  Created by Mauricio Cesar Maniglia Junior on 08/06/18.
//  Copyright © 2018 Mauricio Cesar Maniglia Junior. All rights reserved.
//

import Foundation

extension Data {
    func jsonDictionary() -> [String: Any]? {
        do {
            let json = try JSONSerialization.jsonObject(with: self, options: .allowFragments) as? [String: Any]
            
            return json
        } catch {
            return nil
        }
    }
}
