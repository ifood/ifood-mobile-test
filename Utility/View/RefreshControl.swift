//
//  RefreshControl.swift
//  TwitterSentiment
//
//  Created by Jean Vinge on 29/11/18.
//  Copyright © 2018 Jean Vinge. All rights reserved.
//

import UIKit
import RxCocoa

open class RefreshControl: UIRefreshControl, ConfigurableView {

    // MARK: Init

    public var onRefreshing: Binder<Bool> {
        return Binder(self.rx.base, binding: { (view, isRefreshing) in
            if isRefreshing {
                view.do_beginRefreshingProgrammatically(sendActions: false)
            } else {
                view.endRefreshing()
            }
        })
    }

    public init(with customization: Customization) {
        super.init()
        self.tintColor = customization.titleColor
        self.backgroundColor = customization.backgroundColor
        self.initLayout()
    }

    public override init() {
        super.init(frame: .zero)
        self.initLayout()
    }

    required public init?(coder aDecoder: NSCoder) {
        super.init(coder: aDecoder)
        self.initLayout()
    }

    open func initConstraints() {
    }

    open func initSubviews() {
    }
}
