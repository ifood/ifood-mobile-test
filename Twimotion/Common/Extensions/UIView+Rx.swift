//
//  UIView+Rx.swift
//  Twimotion
//
//  Created by Antony on 29/05/18.
//  Copyright © 2018 Twimotion. All rights reserved.
//

import Foundation
import UIKit
import RxSwift
import RxCocoa

extension Reactive where Base: UIView {
    public var backgroundColor: Binder<UIColor> {
        return Binder(base) { view, color in
            view.backgroundColor = color
        }
    }
}
