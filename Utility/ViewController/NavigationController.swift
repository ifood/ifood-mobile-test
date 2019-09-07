//
//  NavigationController.swift
//  TwitterSentiment
//
//  Created by Jean Vinge on 11/07/2018.
//  Copyright © 2018 Atlas Politico. All rights reserved.
//

import UIKit

open class NavigationController: UINavigationController, ConfigurableView {

    // MARK: Init

    public init(_ rootViewController: UIViewController) {
        super.init(rootViewController: rootViewController)
        self.initLayout()
    }

    override init(nibName nibNameOrNil: String?, bundle nibBundleOrNil: Bundle?) {
        super.init(nibName: nibNameOrNil, bundle: nibBundleOrNil)
        self.initLayout()
    }

    public required init?(coder aDecoder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }

    open func initSubviews() {
    }

    open func initConstraints() {
    }
}
