//
//  ErrorDisplayer.swift
//  SentimentAnalysis
//
//  Created by Thales Frigo on 01/12/18.
//  Copyright © 2018 Thales Frigo. All rights reserved.
//

import UIKit

protocol ErrorDisplayer {
    typealias ReloadHandler = () -> Void
    
    func show(_ error: Error, then reloadHandler: @escaping ReloadHandler)
}

extension ErrorDisplayer where Self: UIViewController {
    func show(_ error: Error, then reloadHandler: @escaping ReloadHandler) {
        let errorVC = ErrorViewController()
        errorVC.reloadHandler = { (errorVC) in
            errorVC?.remove()
            reloadHandler()
        }
        add(errorVC)
    }
    
    func show(_ error: Error) {
        show(error, then: {})
    }
}
