//
//  UIAlertController+Extension.swift
//  TwitterAnalyzer
//
//  Created by Bruno Vieira on 21/06/19.
//  Copyright © 2019 Bruno Vieira. All rights reserved.
//

import UIKit

extension UIAlertController {
    static func showAlert(in controller: UIViewController, withTitle title: String?, message: String?, preferredStyle: UIAlertController.Style = .alert, action: UIAlertAction? = nil) {
        let alertController = UIAlertController(title: title, message: message, preferredStyle: preferredStyle)
        let defaultAction = UIAlertAction(title: "OK", style: .default, handler: nil)
        alertController.addAction(action ?? defaultAction)
        controller.present(alertController, animated: true, completion: nil)
    }
}
