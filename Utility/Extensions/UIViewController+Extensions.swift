//
//  UIViewController+Extensions.swift
//  ifood-mobile-test
//
//  Created by Jean Vinge on 24/12/18.
//  Copyright © 2018 Jean Vinge. All rights reserved.
//

import UIKit
import RxSwift
import RxCocoa

extension UIViewController {

    func configureTheme(theme: Theme) {
        self.view.tintColor = theme.view.tintColor
        self.view.backgroundColor = theme.view.backgroundColor
        self.navigationController?.configure(theme: theme)
        self.navigationItem.backBarButtonItem = UIBarButtonItem(title: "", style: .plain, target: nil, action: nil)

        self.tabBarController?.tabBar.tintColor = theme.tabBar.tintColor
        self.tabBarController?.tabBar.barTintColor = theme.tabBar.barTintColor
        self.tabBarController?.tabBar.isTranslucent = theme.tabBar.isTranslucent

        if #available(iOS 10.0, *) {
            self.tabBarController?.tabBar.unselectedItemTintColor = theme.tabBar.unSelectedColor
        } else {
            self.tabBarController?.tabBar.items?.forEach { item in
                item.setTitleTextAttributes([.foregroundColor: theme.tabBar.unSelectedColor], for: .normal)
            }
        }
    }
}

extension Reactive where Base: UIViewController {
    public var viewDidAppear: Driver<Bool> {
        return self.sentMessage(#selector(UIViewController.viewDidAppear(_:))).map { _ in true }.asDriverOnErrorJustComplete()
    }
    public var viewWillAppear: Driver<Bool> {
        return self.sentMessage(#selector(UIViewController.viewWillAppear(_:))).map { _ in true }.asDriverOnErrorJustComplete()
    }
    public var viewWillDisappear: Driver<Bool> {
        return self.sentMessage(#selector(UIViewController.viewWillDisappear(_:))).map { _ in false }.asDriverOnErrorJustComplete()
    }
}
