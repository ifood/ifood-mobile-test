//
//  UIView+Utils.swift
//  Movile-Test
//
//  Created by Thiago Felipe Alves on 14/07/19.
//  Copyright © 2019 Thalves. All rights reserved.
//

import UIKit
//import PKHUD

let kErrorDequeueCellIdenfier = "Could not dequeue cell with identifier"

public extension UIView {
    static var identifier: String { return String(describing: self) }

    static func fromNib<T: UIView>(owner: Any? = nil) -> T {
        guard let result = Bundle.main.loadNibNamed(T.identifier, owner: owner, options: nil)?.first as? T else {
            fatalError("\(kErrorDequeueCellIdenfier): \(T.identifier)")
        }
        return result
    }


//    func showHUD() {
//        PKHUD.sharedHUD.dimsBackground = true
//        let activity = PKHUDProgressView(title: nil, subtitle: "Carregando")
//        activity.clipsToBounds = false
//        PKHUD.sharedHUD.contentView = activity
//        DispatchQueue.main.async {
//            PKHUD.sharedHUD.show()
//        }
//    }
//
//    func hideHUD() {
//        DispatchQueue.main.async {
//            PKHUD.sharedHUD.hide()
//        }
//    }

    func showError(error: NSError, uiview: UIViewController) {

        let alert = UIAlertController(title: "Oops", message: error.domain, preferredStyle: .alert)
        alert.addAction(UIAlertAction(title: "Ok", style: .default, handler: nil))
        uiview.present(alert, animated: true)
    }
}
