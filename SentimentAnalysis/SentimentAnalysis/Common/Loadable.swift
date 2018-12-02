//
//  Loadable.swift
//  SentimentAnalysis
//
//  Created by Thales Frigo on 02/12/18.
//  Copyright © 2018 Thales Frigo. All rights reserved.
//

import UIKit
import NVActivityIndicatorView

protocol Loadable: NVActivityIndicatorViewable {
    func startLoading()
    func stopLoading()
}

extension Loadable where Self: UIViewController {
    
    func startLoading() {
        startAnimating(
            CGSize(width: 50, height: 50),
            type: .circleStrokeSpin,
            color: .red,
            displayTimeThreshold: 3,
            minimumDisplayTime: 3,
            backgroundColor: UIColor.white.withAlphaComponent(0.8)
        )
    }
    
    func stopLoading() {
        stopAnimating()
    }
}
