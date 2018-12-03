//
//  ApperanceProxyHelper.swift
//  SentimentAnalysis
//
//  Created by Thales Frigo on 02/12/18.
//  Copyright © 2018 Thales Frigo. All rights reserved.
//

import Foundation
import UIKit

struct ApperanceProxyHelper {
    
    static func make() {
        let window = UIWindow.appearance()
        window.tintColor = .primary
        
        let navigationBar = UINavigationBar.appearance()
        navigationBar.tintColor = .white
        navigationBar.barTintColor = .primary
        navigationBar.barStyle = .black
        navigationBar.titleTextAttributes = [
            NSAttributedString.Key.foregroundColor: UIColor.white
        ]
        
        let searchBar = UISearchBar.appearance()
        searchBar.tintColor = .white
        searchBar.barStyle = .blackTranslucent
        searchBar.searchBarStyle = .prominent
    }
}
