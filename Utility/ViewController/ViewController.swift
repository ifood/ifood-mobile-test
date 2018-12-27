//
//  ViewController.swift
//  TwitterSentiment
//
//  Created by Jean Vinge on 14/06/2018.
//  Copyright © 2018 Atlas Politico. All rights reserved.
//

import UIKit
import RxSwift
import RxCocoa

open class ViewController<T: UIView>: UIViewController, ConfigurableView {

    // MARK: Var

    public let baseView = T.init()

    public var theme: Theme

    override open var preferredStatusBarStyle: UIStatusBarStyle {
        return theme.view.statusBarStyle
    }

    // MARK: Init

    public init(theme: Theme = HomeTheme()) {
        self.theme = theme
        super.init(nibName: nil, bundle: nil)
        self.view = baseView
        self.initLayout()
    }

    open override func viewWillAppear(_ animated: Bool) {
        super.viewWillAppear(animated)
        self.configureTheme(theme: self.theme)
    }

    required public init?(coder aDecoder: NSCoder) {
        fatalError("init coder not implemented")
    }

    public func initSubviews() {
    }

    public func initConstraints() {
    }
}
