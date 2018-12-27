//
//  Theme.swift
//  TwitterSentiment
//
//  Created by Jean Vinge on 18/06/2018.
//  Copyright © 2018 Atlas Politico. All rights reserved.
//

import UIKit

public struct ViewTheme {
    let backgroundColor: UIColor
    let tintColor: UIColor
    let statusBarStyle: UIStatusBarStyle
}

public struct NavigationTheme {

    var background: UIColor
    var titleColor: UIColor
    var tintColor: UIColor
    var font: UIFont
}

public struct TabBarTheme {
    var tintColor: UIColor
    var barTintColor: UIColor
    var isTranslucent: Bool
    var unSelectedColor: UIColor
}

public protocol Theme {
    var navigationBar: NavigationTheme { get }
    var tabBar: TabBarTheme { get }
    var view: ViewTheme { get }
}

extension Theme {
    public var tabBar: TabBarTheme {
        return TabBarTheme(tintColor: .gray, barTintColor: .black, isTranslucent: false, unSelectedColor: .gray)
    }
}

public struct HomeTheme: Theme {
    public init() {
        
    }
    public var navigationBar: NavigationTheme {
        return NavigationTheme(background: .white, titleColor: .black, tintColor: .black, font: .boldSystemFont(ofSize: 14))
    }
    public var view: ViewTheme {
        return ViewTheme(backgroundColor: .white, tintColor: .white, statusBarStyle: .default)
    }
}

public struct ClearTheme: Theme {
    public init() {
        
    }
    public var navigationBar: NavigationTheme {
        return NavigationTheme(background: .clear, titleColor: .clear, tintColor: .black, font: .boldSystemFont(ofSize: 14))
    }
    public var view: ViewTheme {
        return ViewTheme(backgroundColor: .clear, tintColor: .systemBlue, statusBarStyle: .default)
    }
}
