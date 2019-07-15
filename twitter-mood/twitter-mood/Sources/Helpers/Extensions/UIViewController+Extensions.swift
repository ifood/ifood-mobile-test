//
//  UIViewController+Extensions.swift
//  twitter-mood
//
//  Created by Dennis Merli on 7/9/19.
//  Copyright © 2019 Dennis Merli. All rights reserved.
//

import UIKit

/// Action of Custom Alert Controller
public typealias AlertAction = (title: String, style: UIAlertAction.Style, handler: (() -> Void)?)

extension UIViewController {
    // Show Alert
    func showAlert(alertController: UIAlertController, actions: [AlertAction]) {
        for action in actions {
            let alertAction = UIAlertAction(title: action.title, style: action.style, handler: { (_) in
                guard let handler = action.handler else { return }
                handler()
            })
            
            alertController.addAction(alertAction)
        }
        present(alertController, animated: true, completion: nil)
    }
    
    /// Show error alert based on a request result
    func showRequestErrorAlert(_ error: Error?, actions: [AlertAction] = []) {
        guard let error = error else { return }
        let alert = UIAlertController(title: BaseLocalizable.error, message: error.localizedDescription, preferredStyle: .alert)
        self.showAlert(alertController: alert, actions: actions)
    }
}
