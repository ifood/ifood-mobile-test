//
//  String+Localized.swift
//  iFoodTest
//
//  Created by Mauricio Cesar Maniglia Junior on 10/06/18.
//  Copyright © 2018 Mauricio Cesar Maniglia Junior. All rights reserved.
//

import UIKit

extension String {
    
    func localized() -> String {
        return NSLocalizedString(self, tableName: nil, bundle: Bundle.main, value: "", comment: "")
    }
}
