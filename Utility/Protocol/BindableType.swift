//
//  BindableType.swift
//  TwitterSentiment
//
//  Created by Jean Vinge on 15/06/2018.
//  Copyright © 2018 Atlas Politico. All rights reserved.
//

import UIKit
import RxSwift

public protocol BindableType {
    associatedtype ViewModelType
    var viewModel: ViewModelType! { get set }
    func bindViewModel()
}

extension BindableType where Self: UIViewController {
    public mutating func bind(to model: Self.ViewModelType) {
        viewModel = model
        loadViewIfNeeded()
        bindViewModel()
    }
}
